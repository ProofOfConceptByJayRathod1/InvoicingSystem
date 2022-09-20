package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.entity.AccType;
import com.simform.invoicingsystem.entity.InvoiceCycle;
import com.simform.invoicingsystem.entity.ProjectModel;
import com.simform.invoicingsystem.repository.AccTypeRepository;
import com.simform.invoicingsystem.repository.InvoiceCycleRepository;
import com.simform.invoicingsystem.repository.ProjectModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DropdownService {

    private ProjectModelRepository projectModelRepository;
    private InvoiceCycleRepository invoiceCycleRepository;
    private AccTypeRepository accTypeRepository;

    public DropdownService(ProjectModelRepository projectModelRepository, InvoiceCycleRepository invoiceCycleRepository, AccTypeRepository accTypeRepository) {
        this.projectModelRepository = projectModelRepository;
        this.invoiceCycleRepository = invoiceCycleRepository;
        this.accTypeRepository = accTypeRepository;
    }

    public List<String>  getProjectModel(){
        List<ProjectModel> projectModelList = projectModelRepository.findAll();
        return projectModelList.stream().map(ProjectModel::getModel).collect(Collectors.toList());
    }

    public List<String> getInvoiceCycle(){
        List<InvoiceCycle> invoiceCycleList = invoiceCycleRepository.findAll();
        return invoiceCycleList.stream().map(InvoiceCycle::getCycle).collect(Collectors.toList());
    }

    public List<String> getAccType(){
        List<AccType> accTypeList = accTypeRepository.findAll();
        return accTypeList.stream().map(AccType::getAccType).collect(Collectors.toList());
    }

    public List<String> getPayModel(){
        return Arrays.asList("Prepaid" , "Postpaid");
    }

    public List<String> getActiveBilling(){
        return Arrays.asList("Yes" , "No");
    }

}
