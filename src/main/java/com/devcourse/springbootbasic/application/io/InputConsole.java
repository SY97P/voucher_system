package com.devcourse.springbootbasic.application.io;

import com.devcourse.springbootbasic.application.model.DiscountValue;
import com.devcourse.springbootbasic.application.model.ListMenu;
import com.devcourse.springbootbasic.application.model.Menu;
import com.devcourse.springbootbasic.application.model.VoucherType;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.springframework.stereotype.Component;

@Component
public class InputConsole {

    private static final TextIO textIO = TextIoFactory.getTextIO();

    public Menu readMenu() {
        String input = textIO.newStringInputReader()
                .withInputTrimming(true)
                .read("Menu Selection: ");
        return Menu.getMenu(input);
    }

    public VoucherType readVoucherType() {
        String input = textIO.newStringInputReader()
                .withInputTrimming(true)
                .read("Voucher Type Selection: ");
        return VoucherType.getVoucherType(input);
    }

    public ListMenu readListMenu() {
        String input = textIO.newStringInputReader()
                .withInputTrimming(true)
                .read("List Type Selection: ");
        return ListMenu.getListMenu(input);
    }

    public DiscountValue readDiscountValue(VoucherType voucherType) {
        String inputDiscountValue = textIO.newStringInputReader()
                .withInputTrimming(true)
                .read("Discount Value: ");
        return new DiscountValue(voucherType, inputDiscountValue);
    }

}
