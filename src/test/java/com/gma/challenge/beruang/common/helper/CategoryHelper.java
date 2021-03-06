package com.gma.challenge.beruang.common.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.util.Mapper;

public class CategoryHelper {
  private static Long ID_COUNTER = 1l;

  private static final String CATEGORY_ICON = "fa-solid fa-books";
  private static final boolean CATEGORY_EXPENSE = true;
  private static final String CATEGORY_COLOR = "green";
  private static final String CATEGORY_NAME = "Hobby";

  private static final String UPDATE_CATEGORY_ICON = "updated fa-solid fa-books";
  private static final boolean UPDATE_CATEGORY_EXPENSE = false;
  private static final String UPDATE_CATEGORY_COLOR = "updated green";
  private static final String UPDATE_CATEGORY_NAME = "Updated Hobby";

  public static NewCategoryRequestData getValidNewCategoryRequestDataSample() {
    NewCategoryRequestData requestData = new NewCategoryRequestData();
    requestData.setName(CATEGORY_NAME);
    requestData.setExpense(CATEGORY_EXPENSE);
    requestData.setColor(CATEGORY_COLOR);
    requestData.setIcon(CATEGORY_ICON);

    return requestData;
  }

  public static boolean isCategoryResponseDataEqualsToSample(CategoryResponseData responseData) {
    CategoryData category = responseData.getCategory();

    if (!category.getName().equals(CATEGORY_NAME)) {
      return false;
    }

    if (!category.getExpense().equals(CATEGORY_EXPENSE)) {
      return false;
    }

    if (!category.getColor().equals(CATEGORY_COLOR)) {
      return false;
    }

    if (!category.getIcon().equals(CATEGORY_ICON)) {
      return false;
    }

    return true;
  }

  public static NewCategoryRequestData getInvalidNullNewCategoryRequestDataSample() {
    return null;
  }

  public static NewCategoryRequestData getInvalidIncompleteNewCategoryRequestDataSample() {
    NewCategoryRequestData requestData = new NewCategoryRequestData();
    requestData.setExpense(CATEGORY_EXPENSE);
    requestData.setColor(CATEGORY_COLOR);
    requestData.setIcon(CATEGORY_ICON);

    return requestData;
  }

  public static NewCategoryRequestData getInvalidEmptyNewCategoryRequestDataSample() {
    NewCategoryRequestData requestData = new NewCategoryRequestData();

    return requestData;
  }

  public static UpdateCategoryRequestData getValidFullUpdateCategoryRequestDataSample() {
    UpdateCategoryRequestData requestData = new UpdateCategoryRequestData();
    requestData.setName(UPDATE_CATEGORY_NAME);
    requestData.setExpense(UPDATE_CATEGORY_EXPENSE);
    requestData.setColor(UPDATE_CATEGORY_COLOR);
    requestData.setIcon(UPDATE_CATEGORY_ICON);

    return requestData;
  }

  public static boolean isUpdateCategoryResponseDataEqualsToSample(CategoryResponseData responseData) {
    CategoryData category = responseData.getCategory();

    if (!category.getName().equals(UPDATE_CATEGORY_NAME)) {
      return false;
    }

    if (!category.getExpense().equals(UPDATE_CATEGORY_EXPENSE)) {
      return false;
    }

    if (!category.getColor().equals(UPDATE_CATEGORY_COLOR)) {
      return false;
    }

    if (!category.getIcon().equals(UPDATE_CATEGORY_ICON)) {
      return false;
    }

    return true;
  }

  public static UpdateCategoryRequestData getInvalidEmptyUpdateCategoryRequestDataSample() {
    UpdateCategoryRequestData requestData = new UpdateCategoryRequestData();
    return requestData;
  }

  public static UpdateCategoryRequestData getValidPartialUpdateCategoryRequestDataSample() {
    UpdateCategoryRequestData requestData = new UpdateCategoryRequestData();
    requestData.setExpense(UPDATE_CATEGORY_EXPENSE);
    requestData.setColor(UPDATE_CATEGORY_COLOR);
    return requestData;
  }

  public static boolean isUpdateCategoryResponseDataEqualsToPartialSample(
      CategoryResponseData categoryResponseData) {
    CategoryData category = categoryResponseData.getCategory();
    if (!category.getExpense().equals(UPDATE_CATEGORY_EXPENSE)) {
      return false;
    }

    if (!category.getColor().equals(UPDATE_CATEGORY_COLOR)) {
      return false;
    }

    return true;
  }

  public static List<Long> getCategoryIdsDataSample() {
    return Arrays.asList(Long.valueOf(1), Long.valueOf(2));
  }

  public static UpdateCategoryRequestData getInvalidNullUpdateCategoryRequestDataSample() {
    return null;
  }

  public static List<CategoryData> getCategoryDataSamples(boolean expense) {
    List<CategoryData> categories = new ArrayList<>();

    if (expense) {
      categories.add(CategoryData.builder()
          .id(getIdCounter())
          .name("Food and drinks")
          .expense(true)
          .active(true)
          .color("blue")
          .icon("food")
          .build());

      categories.add(CategoryData.builder()
          .id(getIdCounter())
          .name("Transportation")
          .expense(true)
          .active(true)
          .color("green")
          .icon("bus")
          .build());

      categories.add(CategoryData.builder()
          .id(getIdCounter())
          .name("Mobile data")
          .expense(true)
          .active(true)
          .color("red")
          .icon("mobile-phone")
          .build());

      categories.add(CategoryData.builder()
          .id(getIdCounter())
          .name("Grocery")
          .expense(true)
          .active(true)
          .color("dark-green")
          .icon("grocery-bag")
          .build());

      categories.add(CategoryData.builder()
          .id(getIdCounter())
          .name("Hobby")
          .expense(true)
          .active(true)
          .color("yellow")
          .icon("basketball")
          .build());
    } else {
      categories.add(CategoryData.builder()
          .id(getIdCounter())
          .name("Salary")
          .expense(false)
          .active(true)
          .color("green")
          .icon("money")
          .build());

      categories.add(CategoryData.builder()
          .id(getIdCounter())
          .name("Part time")
          .expense(false)
          .active(true)
          .color("grey")
          .icon("laptop")
          .build());
    }

    return categories;
  }

  private static Long getIdCounter() {
    return ID_COUNTER++;
  }

  public static Category getCategorySample(boolean expense) {
    if (expense) {
      return Mapper.toCategory(CategoryData.builder()
          .id(getIdCounter())
          .name("Food and drinks")
          .expense(true)
          .active(true)
          .color("blue")
          .icon("food")
          .build());
    } else {
      return Mapper.toCategory(CategoryData.builder()
          .id(getIdCounter())
          .name("Salary")
          .expense(false)
          .active(true)
          .color("green")
          .icon("money")
          .build());
    }
  }

  public static List<Category> getCategorySamples(boolean expense) {
    return getCategoryDataSamples(expense).stream().map(categoryData -> Mapper.toCategory(categoryData)).collect(Collectors.toList());
  }

  public static Category getCreateCategorySample() {
    Category category = new Category();
    category.setName(CATEGORY_NAME);
    category.setExpense(CATEGORY_EXPENSE);
    category.setColor(CATEGORY_COLOR);
    category.setIcon(CATEGORY_ICON);

    return category;
  }

  public static Category getCreatedCategorySample() {
    Category category = new Category();
    category.setId(Long.valueOf(1));
    category.setName(CATEGORY_NAME);
    category.setExpense(CATEGORY_EXPENSE);
    category.setColor(CATEGORY_COLOR);
    category.setIcon(CATEGORY_ICON);
    category.setActive(true);

    return category;
  }

}
