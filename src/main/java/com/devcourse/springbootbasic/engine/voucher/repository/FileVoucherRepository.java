package com.devcourse.springbootbasic.engine.voucher.repository;

import com.devcourse.springbootbasic.engine.config.YamlProperties;
import com.devcourse.springbootbasic.engine.exception.InvalidDataException;
import com.devcourse.springbootbasic.engine.config.Message;
import com.devcourse.springbootbasic.engine.voucher.domain.Voucher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@Profile({"default"})
public class FileVoucherRepository implements VoucherRepository {

    private final ConfigurableApplicationContext applicationContext;
    private final YamlProperties yamlProperties;

    FileVoucherRepository(ConfigurableApplicationContext applicationContext, YamlProperties yamlProperties) {
        this.applicationContext = applicationContext;
        this.yamlProperties = yamlProperties;
    }

    private File getFile() {
        try {
            return applicationContext.getResource(
                    String.format("file:%s", yamlProperties.getVoucherRecordPath())
            ).getFile();
        } catch (IOException e) {
            throw new InvalidDataException(Message.INVALID_FILE_ACCESS, e.getCause());
        }
    }

    @Override
    public Voucher insert(Voucher voucher) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getFile(), true);
            String voucherInfo = "%s\n".formatted(voucher.toString());
            fileOutputStream.write(voucherInfo.getBytes());
        } catch (IOException e) {
            throw new InvalidDataException(Message.INVALID_FILE_ACCESS, e.getCause());
        }
        return voucher;
    }

    @Override
    public List<String> findAll() {
        List<String> voucherRecord = new ArrayList<>();
        try {
            File file = getFile();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(file)
            );
            String record;
            while ((record = bufferedReader.readLine()) != null) {
                voucherRecord.add(record);
            }
        } catch (IOException e) {
            throw new InvalidDataException(Message.INVALID_FILE_ACCESS, e.getCause());
        }
        return voucherRecord;
    }

    @Override
    public void setVoucherMap(Map<UUID, Voucher> map) {
    }
}
