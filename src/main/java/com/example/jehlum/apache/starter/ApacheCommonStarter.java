package com.example.jehlum.apache.starter;

import com.example.jehlum.apache.Account;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.arch.Processor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.Fraction;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author irfan.nagoo
 */
public class ApacheCommonStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheCommonStarter.class);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info(LocaleUtils.localeLookupList(Locale.CANADA_FRENCH).toString());
        LOGGER.info(EnumUtils.getEnumList(Account.AccountType.class).toString());
        Processor processor = ArchUtils.getProcessor();
        LOGGER.info(processor.getArch().getLabel());
        int[] intArray = new int[]{1, 2, 3, 4, 5, 6, 7};
        ArrayUtils.shuffle(intArray);
        String result = Arrays.stream(intArray)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        LOGGER.info(result);
        int median = ObjectUtils.median(ArrayUtils.toObject(intArray));
        LOGGER.info(String.valueOf(median));
        LOGGER.info(RegExUtils.removeAll("in10\nside\n45", Pattern.compile("[0-9\n]")));
        LOGGER.info(RegExUtils.replaceAll("in10\rside\n45", "[\\d\n\r]", ":"));
        LOGGER.info(StringUtils.leftPad("00011", 7, '0'));
        LOGGER.info(String.valueOf(StringUtils.isAlpha("AwsService")));
        Account account = new Account(100L, Account.AccountType.CURRENT, "SB12345", LocalDate.now());
        LOGGER.info(new ReflectionToStringBuilder(account, ToStringStyle.MULTI_LINE_STYLE).toString());

        LOGGER.info(String.valueOf(NumberUtils.isCreatable("1234")));
        LOGGER.info(String.valueOf(NumberUtils.max(1, 34, 76, 98, 1002)));
        LOGGER.info(String.valueOf(NumberUtils.toScaledBigDecimal(100.40f, 2, RoundingMode.FLOOR)));
        LOGGER.info(String.valueOf(Fraction.getFraction("3/2")));

        LocalDateTime localDateTime = LocalDateTime.now();
        LOGGER.info(DateTimeFormatter.ofPattern("dd MMM, yyyy HH:mm:ss").format(localDateTime));
        LOGGER.info(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(localDateTime));
        LOGGER.info(DateTimeFormatter.ofPattern("dd MMM, yyyy HH:mm:ss", Locale.CANADA_FRENCH)
                .format(localDateTime));

        LOGGER.info(LocalDateTime.now(ZoneId.of("Asia/Kolkata")).toString());
    }


}
