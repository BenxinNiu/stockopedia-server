package MUN.Service;
import MUN.MongoDocument.InventoryConsolidator;
import MUN.MongoRepo.InventoryCollectionRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchInventory {
    @Autowired
    private InventoryCollectionRepo inventory;

    public List<InventoryConsolidator> findAll (){
        return inventory.findAll();
    }
}
