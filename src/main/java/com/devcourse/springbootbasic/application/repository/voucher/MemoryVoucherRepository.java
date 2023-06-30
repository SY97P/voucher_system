package com.devcourse.springbootbasic.application.repository.voucher;

import com.devcourse.springbootbasic.application.domain.Voucher;
import com.devcourse.springbootbasic.application.domain.VoucherMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@Profile({"dev"})
public class MemoryVoucherRepository implements VoucherRepository {

    private final VoucherMap voucherMap;

    public MemoryVoucherRepository() {
        this.voucherMap = new VoucherMap(new HashMap<>());
    }

    @Override
    public Voucher insert(Voucher voucher) {
        voucherMap.addVoucher(voucher);
        return voucher;
    }

    @Override
    public List<String> findAll() {
        return voucherMap.getAllVouchers();
    }

}
