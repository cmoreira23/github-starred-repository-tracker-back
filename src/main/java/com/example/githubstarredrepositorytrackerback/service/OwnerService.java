/**
 * 
 */
package com.example.githubstarredrepositorytrackerback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.githubstarredrepositorytrackerback.model.Owner;
import com.example.githubstarredrepositorytrackerback.repository.OwnerRepository;

/**
 * @author Camilla Cyrino
 *
 */
@Service
public class OwnerService {

	@Autowired
	private OwnerRepository repository;

	/**
	 * Returns all Owners
	 * 
	 * @return owners {@link List}<{@link Owner}>
	 * @throws Exception {@link Exception}
	 */
	public List<Owner> getAll() throws Exception {
		return repository.findAll();
	}

	/**
	 * Retrieves an Owner by its id.
	 * 
	 * @param id {@link Long}
	 * @return owner {@link Optional}<{@link Owner}>
	 * @throws Exception {@link Exception}
	 */
	public Optional<Owner> getById(final Long id) throws Exception {
		return repository.findById(id);
	}

	/**
	 * Retrieves an Owner by its GitId. '
	 * 
	 * @param gitId {@link Long}
	 * @return owner {@link Optional}<{@link Owner}>
	 * @throws Exception {@link Exception}
	 */
	public Owner getByGitId(final Long gitId) throws Exception {
		return repository.findByGitId(gitId);
	}

	/**
	 * Saves Owner entity
	 * 
	 * @param owner {@link Owner}
	 * @return owner {@link Owner}
	 * @throws Exception {@link Exception}
	 */
	public Owner save(final Owner owner) throws Exception {
		return repository.saveAndFlush(owner);
	}

	/**
	 * Deletes the Owner with the given id
	 * 
	 * @param id {@link Long}
	 * @throws Exception {@link Exception}
	 */
	public void deleteById(final Long id) throws Exception {
		repository.deleteById(id);
	}

	/**
	 * Deletes all Owner
	 * 
	 * @throws Exception {@link Exception}
	 */
	public void deleteAll() throws Exception {
		repository.deleteAll();
	}

	/**
	 * Deletes a given Owner
	 * 
	 * @param owner {@link Owner}
	 * @throws Exception {@link Exception}
	 */
	public void deleteByOwner(final Owner owner) throws Exception {
		repository.delete(owner);
	}

	/**
	 * Create or update a owner
	 * 
	 * @param owner {@link Owner}
	 * @return owner {@link Owner}
	 * @throws Exception {@link Exception}
	 */
	public Owner createOrUpdate(Owner owner) throws Exception {
		Owner ownerDB = getByGitId(owner.getGitId());
		Boolean update = false;
		if (ownerDB != null) {
			update = isChanged(owner, ownerDB, update);
			if (update) {
				prepareToUpdate(owner, ownerDB);
				owner = save(ownerDB);
			}
		} else {
			owner = save(owner);
		}
		return owner;
	}

	/**
	 * Checks if the owner has been changed
	 * 
	 * @param owner   {@link Owner}
	 * @param ownerDB {@link Owner}
	 * @param update  {@link Boolean}
	 * @return update {@link Boolean}
	 */
	private Boolean isChanged(Owner owner, Owner ownerDB, Boolean changed) {
		if (!ownerDB.equals(owner))
			changed = true;

		return changed;
	}

	/**
	 * Prepares Owner to update
	 * 
	 * @param owner   {@link Owner}
	 * @param ownerDB {@link Owner}
	 */
	private void prepareToUpdate(Owner owner, Owner ownerDB) {
		ownerDB.setAvatar(owner.getAvatar());
		ownerDB.setName(owner.getName());
		ownerDB.setType(owner.getType());
		ownerDB.setUrl(owner.getUrl());
	}
}
