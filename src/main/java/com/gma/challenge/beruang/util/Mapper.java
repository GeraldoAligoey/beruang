package com.gma.challenge.beruang.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.gma.challenge.beruang.data.BudgetData;
import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.NewBudgetRequestData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.TransactionData;
import com.gma.challenge.beruang.data.UpdateBudgetRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateWalletRequestData;
import com.gma.challenge.beruang.data.WalletData;
import com.gma.challenge.beruang.domain.Budget;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.domain.Transaction;
import com.gma.challenge.beruang.domain.Wallet;

public class Mapper {

  public static CategoryData toCategoryData(Category category) {
    CategoryData categoryData = new CategoryData();
    categoryData.setId(category.getId());
    categoryData.setName(category.getName());
    categoryData.setExpense(category.isExpense());
    categoryData.setIcon(category.getIcon());
    categoryData.setColor(category.getColor());
    categoryData.setActive(category.isActive());

    return categoryData;
  }

  public static Category toCategory(CategoryData categoryData) {
    Category category = new Category();
    category.setId(categoryData.getId());
    category.setName(categoryData.getName());
    category.setExpense(categoryData.getExpense());
    category.setIcon(categoryData.getIcon());
    category.setColor(categoryData.getColor());
    category.setActive(true);

    return category;
  }

  public static Category toCategory(NewCategoryRequestData requestData) {
    Category category = new Category();
    category.setName(requestData.getName());
    category.setExpense(requestData.getExpense());
    category.setIcon(requestData.getIcon());
    category.setColor(requestData.getColor());
    category.setActive(true);

    return category;
  }

  public static Category updateCategory(Category category, UpdateCategoryRequestData requestData) {
    if (requestData.getName() != null && !requestData.getName().isBlank()) {
      category.setName(requestData.getName());
    }

    if (requestData.getExpense() != null) {
      category.setExpense(requestData.getExpense());
    }

    if (requestData.getIcon() != null && !requestData.getIcon().isBlank()) {
      category.setIcon(requestData.getIcon());
    }

    if (requestData.getColor() != null && !requestData.getColor().isBlank()) {
      category.setColor(requestData.getColor());
    }

    return category;
  }

  public static WalletData toWalletData(Wallet wallet) {
    WalletData walletData = new WalletData();
    walletData.setId(wallet.getId());
    walletData.setName(wallet.getName());
    walletData.setDefaultCurrencyCode(wallet.getDefaultCurrencyCode());
    walletData.setDefaultWallet(wallet.isDefaultWallet());
    walletData.setInitialBalanceAmount(wallet.getInitialBalanceAmount());

    List<CategoryData> categoryDatas = wallet.getCategories()
        .stream()
        .map(category -> toCategoryData(category))
        .collect(Collectors.toList());

    walletData.setCategories(categoryDatas);

    return walletData;
  }

  public static Wallet toWallet(NewWalletRequestData requestData) {
    Wallet wallet = new Wallet();
    wallet.setName(requestData.getName());
    wallet.setDefaultCurrencyCode(requestData.getDefaultCurrencyCode());

    Set<Category> categories = requestData.getCategoryIds()
        .stream()
        .map(categoryId -> toCategory(categoryId))
        .collect(Collectors.toSet());

    wallet.setCategories(categories);

    if (requestData.getInitialBalanceAmount() == null) {
      wallet.setInitialBalanceAmount(BigDecimal.ZERO);
    } else {
      wallet.setInitialBalanceAmount(requestData.getInitialBalanceAmount());
    }

    if (requestData.getDefaultWallet() != null) {
      wallet.setDefaultWallet(requestData.getDefaultWallet());
    }

    return wallet;
  }

  public static Category toCategory(Long categoryId) {
    Category category = new Category();
    category.setId(categoryId);

    return category;
  }

  public static Wallet updateWallet(Wallet wallet, UpdateWalletRequestData requestData) {
    if (requestData.getName() != null && !requestData.getName().isBlank()) {
      wallet.setName(requestData.getName());
    }

    if (requestData.getDefaultWallet() != null) {
      wallet.setDefaultWallet(requestData.getDefaultWallet());
    }

    if (requestData.getDefaultCurrencyCode() != null && !requestData.getDefaultCurrencyCode().isBlank()) {
      wallet.setDefaultCurrencyCode(requestData.getDefaultCurrencyCode());
    }

    if (requestData.getInitialBalanceAmount() != null) {
      wallet.setInitialBalanceAmount(requestData.getInitialBalanceAmount());
    }

    if (requestData.getCategoryIds() != null && !requestData.getCategoryIds().isEmpty()) {
      Set<Category> categories = requestData.getCategoryIds()
          .stream()
          .map(categoryData -> toCategory(categoryData))
          .collect(Collectors.toSet());
      wallet.setCategories(categories);
    }

    return wallet;
  }

  public static BudgetData toBudgetData(Budget budget) {
    BudgetData budgetData = new BudgetData();
    budgetData.setId(budget.getId());
    budgetData.setName(budget.getName());
    budgetData.setPeriod(budget.getPeriod());
    budgetData.setLimitAmount(budget.getLimitAmount());
    budgetData.setCurrentAmount(budget.getCurrentAmount());
    budgetData.setWallet(toWalletData(budget.getWallet()));
    budgetData.setCategories(toCategoriesData(budget.getCategories()));

    return budgetData;
  }

  public static List<CategoryData> toCategoriesData(Collection<Category> categories) {
    if (categories != null) {
      return categories.stream()
          .map(category -> toCategoryData(category))
          .collect(Collectors.toList());
    }

    return null;
  }

  public static Budget toBudget(NewBudgetRequestData newBudgetRequestData) {
    Budget budget = new Budget();
    BeanUtils.copyProperties(newBudgetRequestData, budget);

    return budget;
  }

  public static Budget updateBudget(Budget budget, UpdateBudgetRequestData requestData) {
    if (requestData.getName() != null && !requestData.getName().isBlank()) {
      budget.setName(requestData.getName());
    }

    if (requestData.getPeriod() != null && !requestData.getPeriod().isBlank()) {
      budget.setPeriod(requestData.getPeriod());
    }

    if (requestData.getLimitAmount() != null) {
      budget.setLimitAmount(requestData.getLimitAmount());
    }

    if (requestData.getCategoryIds() != null && !requestData.getCategoryIds().isEmpty()) {
      Set<Category> categories = requestData.getCategoryIds()
          .stream()
          .map(categoryData -> toCategory(categoryData))
          .collect(Collectors.toSet());
      budget.setCategories(categories);
    }

    return budget;
  }

  public static TransactionData toTransactionData(Transaction transaction) {
    TransactionData transactionData = new TransactionData();
    BeanUtils.copyProperties(transaction, transactionData);    

    return transactionData;
  }

}
