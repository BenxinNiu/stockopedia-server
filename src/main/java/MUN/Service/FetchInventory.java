package MUN.Service;

import MUN.MongoDocument.InventoryConsolidator;
import MUN.MongoDocument.QInventoryConsolidator;
import MUN.MongoRepo.InventoryCollectionRepo;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FetchInventory {

@Autowired
  private   InventoryCollectionRepo inventoryRepo;

public List<InventoryConsolidator> findCompanyByNameLikeOrTicker(String name){
    QInventoryConsolidator query=new QInventoryConsolidator("inventory");
    BooleanExpression byLikeName= query.company_name.containsIgnoreCase(name);
    BooleanExpression byStartsWith=query.company_name.startsWithIgnoreCase(name);
    List<InventoryConsolidator> result_list = (List<InventoryConsolidator>) inventoryRepo.findAll(byLikeName.and(byStartsWith));
    List<InventoryCollectionRepo> filtered_list=new ArrayList<>();
    return result_list;
}

}
