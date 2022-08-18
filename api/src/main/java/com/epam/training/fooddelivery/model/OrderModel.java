package com.epam.training.fooddelivery.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * OrderModel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-18T11:59:28.854894500+03:00[Europe/Minsk]")
public class OrderModel   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("price")
  private BigDecimal price;

  @JsonProperty("timestampCreated")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime timestampCreated;

  @JsonProperty("orderItemModels")
  @Valid
  private List<OrderItemModel> orderItemModels = null;

  public OrderModel id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(example = "4", value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderModel price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public OrderModel timestampCreated(LocalDateTime timestampCreated) {
    this.timestampCreated = timestampCreated;
    return this;
  }

  /**
   * Get timestampCreated
   * @return timestampCreated
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDateTime getTimestampCreated() {
    return timestampCreated;
  }

  public void setTimestampCreated(LocalDateTime timestampCreated) {
    this.timestampCreated = timestampCreated;
  }

  public OrderModel orderItemModels(List<OrderItemModel> orderItemModels) {
    this.orderItemModels = orderItemModels;
    return this;
  }

  public OrderModel addOrderItemModelsItem(OrderItemModel orderItemModelsItem) {
    if (this.orderItemModels == null) {
      this.orderItemModels = new ArrayList<>();
    }
    this.orderItemModels.add(orderItemModelsItem);
    return this;
  }

  /**
   * Get orderItemModels
   * @return orderItemModels
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<OrderItemModel> getOrderItemModels() {
    return orderItemModels;
  }

  public void setOrderItemModels(List<OrderItemModel> orderItemModels) {
    this.orderItemModels = orderItemModels;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderModel orderModel = (OrderModel) o;
    return Objects.equals(this.id, orderModel.id) &&
        Objects.equals(this.price, orderModel.price) &&
        Objects.equals(this.timestampCreated, orderModel.timestampCreated) &&
        Objects.equals(this.orderItemModels, orderModel.orderItemModels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, price, timestampCreated, orderItemModels);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    timestampCreated: ").append(toIndentedString(timestampCreated)).append("\n");
    sb.append("    orderItemModels: ").append(toIndentedString(orderItemModels)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

