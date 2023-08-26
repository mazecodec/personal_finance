package cl.mazecode.personalfinance.core.application.service.bill;

import cl.mazecode.personalfinance.core.domain.entity.BillEntity;
import cl.mazecode.personalfinance.core.domain.entity.BillItemEntity;
import cl.mazecode.personalfinance.core.domain.model.Bill;
import cl.mazecode.personalfinance.infrastucture.repository.BillRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class BillServiceImp implements BillService {
    @Autowired
    private BillRepository repository;

    @Override
    public Bill save(Bill bill) {
        BillEntity billEntity = new BillEntity();
        HashSet<BillItemEntity> billItemsEntity = new HashSet<>();

        BeanUtils.copyProperties(bill, billEntity);
        BeanUtils.copyProperties(bill.getItems(), billItemsEntity);

        billEntity.setItems(billItemsEntity);

        this.repository.save(billEntity);

        return bill;
    }
}
