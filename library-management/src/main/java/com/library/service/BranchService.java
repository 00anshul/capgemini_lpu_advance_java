package com.library.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.entity.LibraryBranch;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.LibraryBranchRepository;

@Service
public class BranchService {

    private final LibraryBranchRepository branchRepository;

    public BranchService(LibraryBranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Transactional
    public LibraryBranch addBranch(LibraryBranch branch) {
        return branchRepository.save(branch);
    }

    public LibraryBranch getBranchById(Long id) {
        return branchRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Branch not found with id: " + id));
    }

    public List<LibraryBranch> getAllBranches() {
        return branchRepository.findAll();
    }

    @Transactional
    public LibraryBranch updateBranch(Long id, LibraryBranch updatedBranch) {
        LibraryBranch existing = getBranchById(id);
        existing.setName(updatedBranch.getName());
        existing.setLocation(updatedBranch.getLocation());
        existing.setContactNumber(updatedBranch.getContactNumber());
        return branchRepository.save(existing);
    }

    @Transactional
    public void deleteBranch(Long id) {
        getBranchById(id);
        branchRepository.deleteById(id);
    }
}