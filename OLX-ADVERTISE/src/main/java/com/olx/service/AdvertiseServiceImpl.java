package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.olx.dto.AdvertiseDTO;
import com.olx.entity.AdvertiseEntity;
import com.olx.exceptions.InvalidAuthTokenException;
import com.olx.exceptions.InvalidCategoryIdException;
import com.olx.exceptions.InvalidStatusIdException;
import com.olx.repository.AdvertiseRepo;


@Service
public class AdvertiseServiceImpl implements AdvertiseService{
	
	@Autowired
	AdvertiseRepo advertiseRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	LoginServiceDelegate loginServiceDelegate;
	
	@Autowired
	MasterDataDelegate masterDataDelegate;

	//8
	@Override
	public AdvertiseDTO createNewAdvertise(AdvertiseDTO advertiseDto, String authToken) {
		
		if(loginServiceDelegate.isTokenValid(authToken)) {
			AdvertiseEntity advertiseEntity = convertDTOIntoEntity(advertiseDto);
			advertiseEntity.setCreatedDate(LocalDate.now());
			advertiseEntity.setModifiedDate(LocalDate.now());
			advertiseEntity.setActive(true);
			advertiseEntity = advertiseRepo.save(advertiseEntity);
			return convertEntityIntoDTO(advertiseEntity);
			
		}
		else {
			throw new InvalidAuthTokenException();
		}
		
	}

	//9
	@Override
	public AdvertiseDTO updateExistingAdvertisement(AdvertiseDTO advertiseDto, 
			String authToken, int id) {
		if (loginServiceDelegate.isTokenValid(authToken)) {
			Optional<AdvertiseEntity> advertiseEntityOptional = advertiseRepo.findById(id);
			if (advertiseEntityOptional.isPresent()) {
				AdvertiseEntity advertiseEntity = advertiseEntityOptional.get();
				//advertiseEntity.setId(id);
				advertiseEntity.setTitle(advertiseDto.getTitle());
				advertiseEntity.setCategory(advertiseDto.getCategory());
				advertiseEntity.setCategory(advertiseDto.getCategory());
				advertiseEntity.setCreatedDate(advertiseDto.getCreatedDate());
				advertiseEntity.setDescription(advertiseDto.getDescription());
				advertiseEntity.setModifiedDate(advertiseDto.getModifiedDate());
				advertiseEntity.setPrice(advertiseDto.getPrice());
				advertiseEntity.setStatus(advertiseDto.getStatus());
				advertiseEntity.setStatusName(advertiseDto.getStatusName());
				advertiseRepo.save(advertiseEntity);
			}
			else
				throw new InvalidCategoryIdException();
		}
		 
		throw new InvalidAuthTokenException();
		
	}

	//10
	@Override
	public List<AdvertiseDTO> getAllUserAdvertisements(String authToken) {
		if (loginServiceDelegate.isTokenValid(authToken)) {
			List<AdvertiseEntity> advertiseEntities = advertiseRepo.findAll();
			List<AdvertiseDTO> advertiseDTO = new ArrayList<>();
			Iterator<AdvertiseEntity> itrAdvertiseEntities = advertiseEntities.iterator();
			while (itrAdvertiseEntities.hasNext()) {
				AdvertiseDTO advertise = convertEntityIntoDTO(itrAdvertiseEntities.next());
				advertiseDTO.add(advertise);
			}
			
		}
			
		throw new InvalidAuthTokenException();
		
			
	}

	//11
	@Override
	public AdvertiseDTO getUserAdvertisementById(String authToken, int id) {
		if (loginServiceDelegate.isTokenValid(authToken)) {
			Optional<AdvertiseEntity> advertiseEntitiesOp = advertiseRepo.findById(id);
			if (advertiseEntitiesOp.isPresent()) {
				AdvertiseDTO advertise = convertEntityIntoDTO(advertiseEntitiesOp.get());
				return advertise;
			}		
		}
		throw new InvalidAuthTokenException(authToken);
	}

	//12
	@Override
	public boolean deleteAdvertisementById(String authToken, int id) {
		if (loginServiceDelegate.isTokenValid(authToken)) {
			advertiseRepo.deleteById(id);
			return true;
		} 
		else
			throw new InvalidAuthTokenException();
	}
	
	//13
	@Override
	public List<AdvertiseDTO> searchAdvertisesByFilterCriteria(String searchText, int categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			int startIndex, int records) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> criteriaQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> rootEntity = criteriaQuery.from(AdvertiseEntity.class);

		Predicate predicateTitle = criteriaBuilder.and();
		Predicate predicateDescription = criteriaBuilder.and();
		Predicate predicateSearchText = criteriaBuilder.and();
		Predicate predicateCategory = criteriaBuilder.and();
		Predicate predicateDateCondition = criteriaBuilder.and();
		Predicate predicatePostedBy = criteriaBuilder.and();
		Predicate predicateFinal = criteriaBuilder.and();

		if(searchText!=null && !"".equalsIgnoreCase(searchText)) {
			predicateTitle = criteriaBuilder.like(rootEntity.<String>get("title"), "%"+searchText+"%");
			predicateDescription = criteriaBuilder.like(rootEntity.<String>get("description"), "%"+searchText+"%");
			predicateCategory = criteriaBuilder.like(rootEntity.<String>get("category"), "%"+searchText+"%");
			predicateSearchText = criteriaBuilder.or(predicateTitle, predicateDescription);
		}
		else if (dateCondition.equalsIgnoreCase("equals")) {
			Predicate predicateEquals = criteriaBuilder.equal(
					rootEntity.get("createdDate"), onDate);
			predicateDateCondition = criteriaBuilder.and(predicateEquals);
		}
		else if(dateCondition.equalsIgnoreCase("greater than")){
			Predicate predicateGreaterThan = criteriaBuilder.greaterThan(
					rootEntity.get("createdDate"), fromDate);
			predicateDateCondition = criteriaBuilder.and(predicateGreaterThan);
		}
		else if(dateCondition.equalsIgnoreCase("less than")) {
			Predicate predicateLessThan = criteriaBuilder.lessThan(
					rootEntity.get("createdDate"), fromDate);
			predicateDateCondition = criteriaBuilder.and(predicateLessThan);
		}
		else if(dateCondition.equalsIgnoreCase("between")) {
			Predicate predicateBetween = criteriaBuilder.between(
					rootEntity.get("createdDate"), fromDate, toDate);
			predicateDateCondition = criteriaBuilder.and(predicateBetween);
		}
		//Write a code to create predicates for  categoryId, posted_by etc.
		predicateFinal = criteriaBuilder.and(predicateSearchText, predicateCategory, predicateDateCondition,
										predicatePostedBy);
		criteriaQuery.where(predicateFinal);
		criteriaQuery.orderBy(criteriaBuilder.asc(rootEntity.get("title")));
		criteriaQuery.orderBy(criteriaBuilder.asc(rootEntity.get("price")));
		
		TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(startIndex);
		typedQuery.setMaxResults(records);
		List<AdvertiseEntity> advertiseEntityList = typedQuery.getResultList();
		return convertEntityListIntoDTOList(advertiseEntityList);
	}
	
	//14
	@Override
	public List<AdvertiseDTO> searchAdvertiseByText(String searchText) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> advertiseQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> advertiseRoot = advertiseQuery.from(AdvertiseEntity.class);

		Predicate predicateTitle = criteriaBuilder.and();
		Predicate predicateDescription = criteriaBuilder.and();
		Predicate predicateSearchText = criteriaBuilder.and();
		if (searchText != null && !searchText.equalsIgnoreCase("")) {
			predicateTitle = criteriaBuilder.like(advertiseRoot.get("title"), "%" + searchText + "%");
			predicateDescription = criteriaBuilder.like(advertiseRoot.get("description"), "%" + searchText + "%");
			predicateSearchText = criteriaBuilder.or(predicateTitle, predicateDescription);
			advertiseQuery.where(predicateSearchText);

			TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(advertiseQuery);

			List<AdvertiseEntity> advertiseEntityList = typedQuery.getResultList();
			return convertEntityListIntoDTOList(advertiseEntityList);

		}
		else
			throw new InvalidStatusIdException();
	}

	//15
	@Override
	public List<AdvertiseDTO> getAdvertisementDetails(String authToken, int id) {
		if (loginServiceDelegate.isTokenValid(authToken)) {
			List<AdvertiseEntity> advertiseEntities = advertiseRepo.findAllById(id);
			List<AdvertiseDTO> advertiseDTO = new ArrayList<>();
			Iterator<AdvertiseEntity> itrAdvertiseEntities = advertiseEntities.iterator();
			while (itrAdvertiseEntities.hasNext()) {
				AdvertiseDTO advertise = convertEntityIntoDTO(itrAdvertiseEntities.next());
				advertiseDTO.add(advertise);
			}

			return advertiseDTO;
		} 
		else
			throw new InvalidAuthTokenException();
	}

	private List<AdvertiseDTO> convertEntityListIntoDTOList(List<AdvertiseEntity> advertiseEntityList) {
		List<AdvertiseDTO> advertiseDtoList = new ArrayList<>();
			for(AdvertiseEntity advertiseEntity: advertiseEntityList) {
				AdvertiseDTO advertiseDto = convertEntityIntoDTO(advertiseEntity);
				advertiseDtoList.add(advertiseDto);
			}
		return advertiseDtoList;
	}
	
	private AdvertiseEntity convertDTOIntoEntity(AdvertiseDTO advertiseDto) {
		return modelMapper.map(advertiseDto, AdvertiseEntity.class);
	}
	
	private AdvertiseDTO convertEntityIntoDTO(AdvertiseEntity advertiseEntity) {
		return modelMapper.map(advertiseEntity, AdvertiseDTO.class);
	}

	
	
	
}
