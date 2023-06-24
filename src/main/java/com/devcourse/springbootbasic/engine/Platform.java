package com.devcourse.springbootbasic.engine;

import com.devcourse.springbootbasic.engine.exception.InvalidDataException;
import com.devcourse.springbootbasic.engine.io.InputConsole;
import com.devcourse.springbootbasic.engine.io.OutputConsole;
import com.devcourse.springbootbasic.engine.model.ListMenu;
import com.devcourse.springbootbasic.engine.model.Menu;
import com.devcourse.springbootbasic.engine.model.VoucherType;
import com.devcourse.springbootbasic.engine.voucher.domain.Voucher;
import com.devcourse.springbootbasic.engine.voucher.service.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Platform {

    private static final Logger logger = LoggerFactory.getLogger(Platform.class);

    @Autowired
    private InputConsole input;

    @Autowired
    private OutputConsole output;

    @Autowired
    private VoucherService voucherService;

    public void run() {
        logger.debug("Program Engine Created");
        while (true) {
            if (getUserMenuInput()) {
                logger.debug("Program Engine Destroyed");
                break;
            }
        }
    }

    private boolean getUserMenuInput() {
        try {
            Menu menu = input.inputMenu();
            return branchByMenu(menu);
        } catch (InvalidDataException | IOException e) {
            output.printError(e);
        }
        return false;
    }

    private boolean branchByMenu(Menu menu) throws IOException {
        return switch (menu) {
            case EXIT -> {
                logger.debug("EXIT MENU selected");
                output.endPlatform();
                yield true;
            }
            case CREATE -> {
                logger.debug("CREATE MENU selected");
                Voucher voucher = createVoucherTask();
                output.printVoucher(voucher);
                output.printMessage(OutputConsole.CREATION_DONE);
                logger.debug("Voucher Creation Success");
                yield false;
            }
            case LIST -> {
                logger.debug("LIST MENU selected");
                branchListMenu();
                yield false;
            }
        };
    }

    private void branchListMenu() throws IOException {
        ListMenu listMenu = input.inputListMenu();
        switch (listMenu) {
            case VOUCHER_LIST -> {
                logger.debug("VOUCHER LIST MENU selected");
                listVoucherTask();
                logger.debug("Voucher Record Task Success");
            }
            case BLACK_CUSTOMER_LIST -> {
                logger.debug("BLACK CUSTOMER LIST MENU selected");
                listBlackCustomerTask();
                logger.debug("Black Customer Task Success");
            }
        }
    }

    private void listBlackCustomerTask() throws IOException {
        output.printBlackCustomers(input.getBlackCustomers());
    }

    private void listVoucherTask() {
        output.printVouchers(voucherService.getAllVouchers());
    }

    private Voucher createVoucherTask() {
        VoucherType voucherType = voucherTypeInput();
        double voucherDiscount = voucherDiscountInput(voucherType);
        Voucher voucher = voucherType.getVoucherFactory()
                .create(voucherDiscount);
        return voucherService.createVoucher(voucher);
    }

    private VoucherType voucherTypeInput() {
        return input.inputVoucherType();
    }

    private double voucherDiscountInput(VoucherType voucherType) {
        return input.inputVoucherDiscount(voucherType);
    }
}
