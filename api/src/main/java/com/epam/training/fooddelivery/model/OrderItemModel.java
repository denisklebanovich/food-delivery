package com.epam.training.fooddelivery.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * OrderItemModel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-12T16:41:56.404299400+03:00[Europe/Minsk]")
public class OrderItemModel   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("foodModel")
  private FoodModel foodModel;

  @JsonProperty("pieces")
  private Integer pieces;

  @JsonProperty("price")
  private BigDecimal price = null;

  public OrderItemModel id(Long id) {
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

  public OrderItemModel foodModel(FoodModel foodModel) {
    this.foodModel = foodModel;
    return this;
  }

  /**
   * Get foodModel
   * @return foodModel
  */
  @ApiModelProperty(value = "")

  @Valid

  public FoodModel getFoodModel() {
    return foodModel;
  }

  public void setFoodModel(FoodModel foodModel) {
    this.foodModel = foodModel;
  }

  public OrderItemModel pieces(Integer pieces) {
    this.pieces = pieces;
    return this;
  }

  /**
   * Get pieces
   * @return pieces
  */
  @ApiModelProperty(value = "")


  public Integer getPieces() {
    return pieces;
  }

  public void setPieces(Integer pieces) {
    this.pieces = pieces;
  }

  public OrderItemModel price(BigDecimal price) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderItemModel orderItemModel = (OrderItemModel) o;
    return Objects.equals(this.id, orderItemModel.id) &&
        Objects.equals(this.foodModel, orderItemModel.foodModel) &&
        Objects.equals(this.pieces, orderItemModel.pieces) &&
        Objects.equals(this.price, orderItemModel.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, foodModel, pieces, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderItemModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    foodModel: ").append(toIndentedString(foodModel)).append("\n");
    sb.append("    pieces: ").append(toIndentedString(pieces)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
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

