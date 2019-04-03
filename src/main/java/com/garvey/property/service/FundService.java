package com.garvey.property.service;

import com.garvey.property.dao.ExpenseGroupMapper;
import com.garvey.property.dao.ExpenseItemPropMapper;
import com.garvey.property.dao.IncomeGroupMapper;
import com.garvey.property.dao.IncomeItemPropMapper;
import com.garvey.property.model.ExpenseItem;
import com.garvey.property.model.IncomeItem;
import com.garvey.property.model.User;
import com.garvey.property.util.Web3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

import java.util.*;

/**
 * @author GarveyWong
 * @date 2019/4/3
 */
@Service
public class FundService {
    @Autowired
    private Web3Util web3Util;

    @Autowired
    private ExpenseGroupMapper expenseGroupMapper;

    @Autowired
    private ExpenseItemPropMapper expenseItemPropMapper;

    @Autowired
    private IncomeGroupMapper incomeGroupMapper;

    @Autowired
    private IncomeItemPropMapper incomeItemPropMapper;


    public Map<Integer, List<IncomeItem>> getMonthlyIncomeItems(Credentials credentials) {
        Map<Integer, List<IncomeItem>> monthlyIncomeItems = new HashMap<>();
        int incomeItemsCount = web3Util.getIncomeItemCount(credentials);
        for (int i = incomeItemsCount - 1; i >= 0; --i) {
            IncomeItem incomeItem = web3Util.getIncomeItem(i, credentials);
            if (incomeItem != null) {
                Integer yearMonth = getYearMonth(incomeItem.getTimestamp());
                int dayInMonth = getDayInMonth(incomeItem.getTimestamp());
                incomeItem.setDayInMonth(dayInMonth);
                List<IncomeItem> incomeItems = monthlyIncomeItems.getOrDefault(yearMonth, new ArrayList<>());
                incomeItems.add(incomeItem);
                monthlyIncomeItems.put(yearMonth, incomeItems);
            }
        }
        return monthlyIncomeItems;
    }

    public Map<Integer, List<IncomeItem>> mockMonthlyIncomeItems(Credentials credentials) {
        Map<Integer, List<IncomeItem>> monthlyIncomeItems = new HashMap<>();
        int incomeItemsCount = 20;
        for (int i = incomeItemsCount - 1; i >= 0; --i) {
            IncomeItem incomeItem = new IncomeItem();
            incomeItem.setIdx(i);
            incomeItem.setDesc("收入Mock " + i);
            incomeItem.setAmountInCents(i + 100);
            incomeItem.setPayer("Garvey");
            incomeItem.setPayerName("Wong");
            incomeItem.setRecorderHash("Hash");
            incomeItem.setRecorderName("Name");
            incomeItem.setTimestamp(1554277649000L - i * 86400000L);
            Integer yearMonth = getYearMonth(incomeItem.getTimestamp());
            int dayInMonth = getDayInMonth(incomeItem.getTimestamp());
            incomeItem.setDayInMonth(dayInMonth);
            List<IncomeItem> incomeItems = monthlyIncomeItems.getOrDefault(yearMonth, new ArrayList<>());
            incomeItems.add(incomeItem);
            monthlyIncomeItems.put(yearMonth, incomeItems);
        }
        return monthlyIncomeItems;
    }

    public Map<Integer, List<ExpenseItem>> getMonthlyExpenseItems(Credentials credentials) {
        Map<Integer, List<ExpenseItem>> monthlyExpenseItems = new HashMap<>();
        int expenseItemsCount = web3Util.getExpenseItemCount(credentials);
        for (int i = expenseItemsCount - 1; i >= 0; --i) {
            ExpenseItem expenseItem = web3Util.getExpenseItem(i, credentials);
            if (expenseItem != null) {
                Integer yearMonth = getYearMonth(expenseItem.getTimestamp());
                List<ExpenseItem> expenseItems = monthlyExpenseItems.getOrDefault(yearMonth, new ArrayList<>());
                expenseItems.add(expenseItem);
                monthlyExpenseItems.put(yearMonth, expenseItems);
            }
        }
        return monthlyExpenseItems;
    }

    public Map<Integer, List<ExpenseItem>> mockMonthlyExpenseItems(Credentials credentials) {
        Map<Integer, List<ExpenseItem>> monthlyIncomeItems = new HashMap<>();
        int incomeItemsCount = 200;
        for (int i = incomeItemsCount - 1; i >= 0; --i) {
            ExpenseItem expenseItem = new ExpenseItem();
            expenseItem.setIdx(i);
            expenseItem.setDesc("收入Mock " + i);
            expenseItem.setAmountInCents(i + 100);
            expenseItem.setPayee("Garvey");
            expenseItem.setPayeeName("Wong");
            expenseItem.setRecorderHash("Hash");
            expenseItem.setRecorderName("Name");
            expenseItem.setTimestamp(1554277649000L - i * 86400000L);
            Integer yearMonth = getYearMonth(expenseItem.getTimestamp());
            int dayInMonth = getDayInMonth(expenseItem.getTimestamp());
            expenseItem.setDayInMonth(dayInMonth);
            List<ExpenseItem> incomeItems = monthlyIncomeItems.getOrDefault(yearMonth, new ArrayList<>());
            incomeItems.add(expenseItem);
            monthlyIncomeItems.put(yearMonth, incomeItems);
        }
        return monthlyIncomeItems;
    }

    public void addIncomeItem(IncomeItem incomeItem, User user) {
        web3Util.addIncomeItem(incomeItem, user.getCredentials());
    }

    public void addExpenseItem(ExpenseItem expenseItem, User user) {
        web3Util.addExpenseItem(expenseItem, user.getCredentials());
    }

    private Integer getYearMonth(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return year * 100 + month;
    }

    private int getDayInMonth(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
