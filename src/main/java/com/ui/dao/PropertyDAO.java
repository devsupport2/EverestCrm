package com.ui.dao;

import java.util.List;

import com.ui.model.AreaTitle;
import com.ui.model.PaymentLabel;
import com.ui.model.PaymentSchedule;
import com.ui.model.ProjectPaymentSchedule;
import com.ui.model.ProjectPriceInfo;
import com.ui.model.Property;
import com.ui.model.PropertyArea;
import com.ui.model.PropertyLayout;
import com.ui.model.PropertyPaymentSchedule;
import com.ui.model.PropertyPriceInfo;
import com.ui.model.PropertyRoom;
import com.ui.model.PropertyType;
import com.ui.model.Realestate;
import com.ui.model.RealestateSubcategory;
import com.ui.model.RealestateType;
import com.ui.model.RoomTitle;

public interface PropertyDAO {

	public List<Property> getAllProperty();
	public List<Property> getPropertyByPage(int pagesize, int startindex);
	public List<RealestateType> getRealestateName();
	public List<RealestateSubcategory> getAllRealestateSubcategoryTitleByRealestateId(int realestateid);
	public List<PropertyType> getPropertyName();
	public List<Realestate> getAllRealestateTypeTitleByRealestateSubcategoryId(int realestatesubcategoryid);
	public List<AreaTitle> getAreaName();
	public List<RoomTitle> getRoomName();
	public List<PropertyPriceInfo> getPropertyPriceInfoById(int propertyid);
	public List<ProjectPaymentSchedule> getPropertyPaymentScheduleById(int projectid);
	public List<PropertyPaymentSchedule> getPropertyPaymentsScheduleById(int propertyid);
	public List<ProjectPriceInfo> getPriceInfoById(int projectid, int categoryid, int subcategoryid, int realestateid);
	public Property getCategoryNameById(int realecateid);
	public Property getSubcategoryNameById(int realestatesubid);
	public Property getRealestateNameById(int realestatetypeid);
	public Property getPropertyDetailById(int propertyid);	
	public String addPropertyType(PropertyType p);	
	public String addAreaType(AreaTitle a);
	public String addPriceType(PaymentLabel pl);
	public String addRoomType(RoomTitle r);
	public String addPriceDetailInfo(PropertyPriceInfo ppi);
	public String addProperty(Property pr);
	public String addPropertyPaymentSchedule(PropertyPaymentSchedule pps);
	public String addAreaDetails(PropertyArea pa);
	public int getLastPropertyId();
	public String addRoomDetails(PropertyRoom pr);
	public List<PropertyArea> getPropertyAreaById(int propertyid);
	public void deleteAreaDetail(int propertyareaid);
	public void deleteRoomDetail(int propertyroomid);
	public void deletePriceDetail(int paymentscheduleid);
	public void deletePropertyLayout(int layoutid);
	public void deletePropertyPriceDetail(int propertypriceinfoid);
	public void deletePropertyPaymentSchedule(int id);
	public void deleteTowerName(int towernameid);
	public void deleteProperty(int propertyid);
	public void updatePropertyStatus(int projectid);
	public List<PropertyRoom> getPropertyRoomById(int propertyid);
	public String editProperty(Property pro);
	public List<Property> getPropertyTitle();
	public String addPriceDetails(PaymentSchedule pss);
	public List<PaymentSchedule> getPropertyPriceById(int propertyid);
	public List<Property> searchProperties(String keyword);
	public Property getLastPropertyDetailByProjectId(int projectid);
	public List<Property> getPropertyNumberListById(int projectid, int categoryid, int subcategoryid, int typeid);	
	public List<Property> getPropertyNumberListById(int projectid, int categoryid, int subcategoryid, int typeid, int unitmasterid);	
	public int getLastRealestateTypeId();	
	public int getLastRealestateSubcategoryId();	
	public List<Property> getAllPropertyByProjectId(int projectid);	
	public List<Property> getAllPropertyByProjectIdAndUnitID(int projectid, int projectunitid);
	public List<Realestate> getAllRealestateType();
	public List<Property> getPropertyNumberListForEnquiryById(int projectid, int categoryid, int subcategoryid);
	public String addPropertyLayout(PropertyLayout pl);
	public List<PropertyLayout> getPropertyLayoutById(int propertyid);
}
