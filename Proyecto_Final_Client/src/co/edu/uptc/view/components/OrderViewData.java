package co.edu.uptc.view.components;

import java.util.List;

public record OrderViewData(
        String idOrder,
        String table,
        String time,
        List<String> products
) {}
