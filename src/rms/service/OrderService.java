package rms.service;

import java.util.List;

import rms.po.CustomOrder;
/**
 * 
* @ClassName: OrderService 
* @Description: 处理订单相关业务
* @author random  
* @date 2015年7月22日 下午5:00:23 
*
 */

public interface OrderService {
	/**
	 * 
	* @Title: saveOrder 
	* @Description: 保存订单相关所有信息
	* @param @param customOrder
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void saveOrder(CustomOrder customOrder) throws Exception;
	/**
	 * 
	* @Title: deleteOrder 
	* @Description: 根据订单id删除订单相关信息 
	* @param @param id
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteOrder(Integer id) throws Exception;
	
	/**
	 * 
	* @Title: deleteOrderDetailsBydetailsid 
	* @Description: 根据明细id删除订单明细
	* @param @param id
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void  deleteOrderDetailsBydetailsid(Integer id) throws Exception;
	
	/**
	 * 
	* @Title: updateOrder 
	* @Description: 根据id更新所有信息
	* @param @param customOrder
	* @param @param id
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateOrder(CustomOrder customOrder,Integer id) throws Exception;
	
	
	/**
	 * 
	* @Title: changeOrderStatus 
	* @Description: 改变订单的支付状态
	* @param @param customOrder
	* @param @param  id
	* @param @throws Exception   设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void changeOrderStatus(CustomOrder customOrder,Integer id) throws Exception;
	/**
	 * 
	* @Title: findAllOrder 
	* @Description: 根据订单状态查询所有订单(基础数据) 
	* @param  customorder
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<CustomOrder>    返回类型 
	* @throws
	 */
	public List<CustomOrder> findAllOrderBystatus(CustomOrder customorder) throws Exception;
	/**
	 * 
	* @Title: findOrderdetailsByid 
	* @Description:  根据订单id查询订单明细
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return CustomOrder    返回类型 
	* @throws
	 */
	public CustomOrder findOrderdetailsByid(Integer id) throws Exception;
}