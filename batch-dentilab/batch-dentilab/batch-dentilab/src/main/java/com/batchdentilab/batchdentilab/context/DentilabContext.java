package com.batchdentilab.batchdentilab.context;

import com.batchdentilab.batchdentilab.model.MappingOrder;
import com.batchdentilab.batchdentilab.model.OrderEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Data
@Component
public class DentilabContext {
    private List<OrderEntity> listOrders = new ArrayList<>();
    private List<MappingOrder> listMappingOrders = new ArrayList<>();
}
