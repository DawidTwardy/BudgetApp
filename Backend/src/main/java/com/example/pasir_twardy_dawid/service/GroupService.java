package com.example.pasir_twardy_dawid.service;

import com.example.pasir_twardy_dawid.dto.GroupDto;
import com.example.pasir_twardy_dawid.model.Group;
import com.example.pasir_twardy_dawid.model.Membership;
import com.example.pasir_twardy_dawid.model.User;
import com.example.pasir_twardy_dawid.repository.DebtRepository;
import com.example.pasir_twardy_dawid.repository.GroupRepository;
import com.example.pasir_twardy_dawid.repository.MembershipRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;
    private final MembershipService membershipService;
    private final DebtRepository debtRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @MutationMapping
    public Group createGroup(GroupDto groupDTO) {
        User owner = membershipService.getCurrentUser();
        Group group = new Group();
        group.setName(groupDTO.getName());
        group.setOwner(owner);
        Group savedGroup = groupRepository.save(group);
        Membership membership = new Membership();
        membership.setUser(owner);
        membership.setGroup(savedGroup);
        membershipRepository.save(membership);
        return savedGroup;
    }

    public void deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("Grupa o ID " + id + " nie istnieje");
        }

        debtRepository.deleteAll(debtRepository.findByGroupId(id));
        membershipRepository.deleteAll(membershipRepository.findByGroupId(id));

        groupRepository.deleteById(id);
    }
}
