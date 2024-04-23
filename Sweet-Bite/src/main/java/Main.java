import java.util.List;



import com.tap.daoImpl.MenuDaoImpl;
import com.tap.daoImpl.OrderDaoImpl;
import com.tap.daoImpl.OrderHistoryDaoImpl;
import com.tap.daoImpl.OrderItemDaoImpl;
import com.tap.daoImpl.UserDaoImpl;
import com.tap.daoImpl.RestaurantDaoImpl;
import com.tap.model.Menu;
import com.tap.model.Order;
import com.tap.model.OrderHistory;
import com.tap.model.OrderItem;
import com.tap.model.Restaurant;
import com.tap.model.User;

public class Main {

	public static void main(String[] args) {
	
//		MenuDaoImpl mi=new MenuDaoImpl(); 
//		Menu m=new Menu("menuName",4.34534,"dfgdf","sdfsdf",true,2,4.5f);
//		System.out.println(mi.addMenu(m));
//		System.out.println(mi.getMenu(4));
//		System.out.println(mi.getAllMenu());
//		System.out.println(mi.deleteMenu(4));
		
//		OrderDaoImpl or=new OrderDaoImpl();
//		Order o=new Order(987.345,"cash","dispatched",4,2);
////		System.out.println(or.addOrder(o));
//		System.out.println(or.getAllOrder());
		
//		OrderItem OI=new OrderItem(5,2,"dfsd",4.5f,3,23.234);
//		
//		OrderItemDaoImpl oii=new OrderItemDaoImpl();
////		System.out.println(oii.addOrderItem(OI));
//		System.out.println(oii.getAllOrderItems());
//		
//			OrderHistory oh=new OrderHistory(2,3);
//			OrderHistoryDaoImpl ohi=new OrderHistoryDaoImpl();
//			System.out.println(ohi.addOrderHistory(oh));
		
		
		RestaurantDaoImpl rdi=new RestaurantDaoImpl();
		List<Restaurant> r=rdi.getAllRestaurant();
		for(Restaurant rd:r) {
		System.out.println(rd);
		}
		
	}

}
