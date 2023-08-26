package cl.mazecode.personalfinance.core.application.service.bill;

import cl.mazecode.personalfinance.core.domain.model.Bill;

public interface BillService {
    Bill save(Bill newBill);
}
