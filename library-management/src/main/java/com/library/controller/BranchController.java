package com.library.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.library.entity.LibraryBranch;
import com.library.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/branches")
@Tag(name = "Branch Management")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    @Operation(summary = "Create a new branch")
    public ResponseEntity<LibraryBranch> addBranch(@RequestBody LibraryBranch branch) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(branchService.addBranch(branch));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get branch by ID")
    public ResponseEntity<LibraryBranch> getBranchById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @GetMapping
    @Operation(summary = "Get all branches")
    public ResponseEntity<List<LibraryBranch>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update branch")
    public ResponseEntity<LibraryBranch> updateBranch(@PathVariable Long id,
                                                       @RequestBody LibraryBranch branch) {
        return ResponseEntity.ok(branchService.updateBranch(id, branch));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete branch")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}