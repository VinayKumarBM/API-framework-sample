package com.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDO {

@SerializedName("id")
@Expose
private Long id;
@SerializedName("petId")
@Expose
private Long petId;
@SerializedName("quantity")
@Expose
private Long quantity;
@SerializedName("shipDate")
@Expose
private String shipDate;
@SerializedName("status")
@Expose
private String status;
@SerializedName("complete")
@Expose
private Boolean complete;

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Long getPetId() {
return petId;
}

public void setPetId(Long petId) {
this.petId = petId;
}

public Long getQuantity() {
return quantity;
}

public void setQuantity(Long quantity) {
this.quantity = quantity;
}

public String getShipDate() {
return shipDate;
}

public void setShipDate(String shipDate) {
this.shipDate = shipDate;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public Boolean getComplete() {
return complete;
}

public void setComplete(Boolean complete) {
this.complete = complete;
}

@Override
public String toString() {
return "[\nid: "+ id +",\npetId: "+ petId +",\nquantity: "+ quantity +",\nshipDate: "+ shipDate 
		+",\nstatus: "+ status +",\ncomplete: "+ complete +"\n]";
}

}