package com.api.request.model;

public record CustomerProduct(String serial_number, String imei1, String imei2, String dop, String popurl,
		int mst_model_id, int product_id) {
}
