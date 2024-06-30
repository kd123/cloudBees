package com.booking.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TicketBookingUtil {

    public static List<Integer> convertCommaSeparatedStringToArrayList(String strData){
        return Arrays.stream(strData.split(",")).map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
//        return Stream.of(Integer.parseInt(Arrays.toString(strData.split(",", -1))))
//                .collect(Collectors.toList());
    }

    public static String convertListOfIntegerToCommaSeparatedString(List<Integer> list){
        return StringUtils.join(list,",");
    }
}
