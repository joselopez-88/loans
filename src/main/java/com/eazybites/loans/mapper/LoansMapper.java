package com.eazybites.loans.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.eazybites.loans.model.dto.request.LoansUpdateRequestDto;
import com.eazybites.loans.model.dto.response.LoansResponseDto;
import com.eazybites.loans.model.entity.Loans;

@Mapper(componentModel = "spring")
public interface LoansMapper {

    LoansResponseDto mapToLoansResponseDto(Loans loans);
    // @Mapping(target = "loanId", ignore = true)
    // @Mapping(target = "createdAt", ignore = true)
    // @Mapping(target = "createdBy", ignore = true)
    // @Mapping(target = "updatedAt", ignore = true)
    // @Mapping(target = "updatedBy", ignore = true)
    // Loans mapToLoans(LoansResponseDto loansResponseDto);

    @Mapping(target = "loanId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void updateLoansFromDto(LoansUpdateRequestDto dto, @MappingTarget Loans entity);
    

}
