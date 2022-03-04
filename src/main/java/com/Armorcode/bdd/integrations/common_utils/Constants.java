package com.Armorcode.bdd.integrations.common_utils;

public class Constants {
	public static final String RESOURCE_DIR = "./src/test/resources/";
	public static final String DATAINPUT_DIR="./src/test/resources/ApiTestData/";
	public static final String CONFIG_DIR = "./src/test/resources/Configs/";
	public static final String JSON_CONTENTTYPE="application/json";
	public static final String CART_JSONINPUT_DIR="./src/test/resources/ApiInputData/CartJsonInputData/";
	public static final String ADDRESS_JSONINPUT_DIR="./src/test/resources/ApiInputData/AddressJsonInputData/";
	public static final String FULFILLER_JSONINPUT_DIR="./src/test/resources/ApiInputData/FulFillerJsonInputData/";
	public static final String COUPON_JSONINPUT_DIR="./src/test/resources/ApiInputData/CouponJsonInputData/";
	public static final String PURCHASEHISTORY_JSONINPUT_DIR="./src/test/resources/ApiInputData/PurchaseHistoryJsonInputData/";
	public static final String NEBULLA_JSONINPUT_DIR="./src/test/resources/ApiInputData/NebullaJsonInputData/";
	public static final String JSONINPUT_DIR="./src/test/resources/Input_data/";
	
	public static final String VIDEORECORD_DIR="./VideoRecord";
	public static final String ORDER_COUPON_HEADER="FEBP7DTYWOQEGVPO";
	public static final String ORDER_COUPON_OMSORDERID_HEADER="we8u3289";

	public static final String BEARER_OTP="8055";
	public static final String RETRY_TYPE_OTP="retry";
	public static final String ANDROID_CHANNEL="ANDROID";
	public static final String WEB_CHANNEL="WEB";
	public static final String FULFILLER_HEADER_APP_NAME="DOSA";


	public static final String NEBULA_GRANT_TYPE="authorization_code";
	public static final String NEBULA_BEARER_X_API_KEY="c6ccc936-8500-42b2-b760-2495e2e36bd9";
	public static final String DC_BEARER_X_API_KEY="343dca9e-0976-4d08-9ca5-18f62ff80965";
	public static final String DC_ADDRESS_BEARER_X_API_KEY="9e316d74-a4ec-4f9a-b3b9-bead3c07e86b";
	public static final String DC_COUPON_BEARER_X_API_KEY="bce5bbf5-df72-4f4b-b00b-243b8c751c3f";
	public static final String DC_PURCHASE_HISTORY_X_API_KEY="305b8924-b6e4-466d-83b3-c2499fbcddc5";


	public static final String NEBULA_OTPBASE_URL="https://api-global.preprod.decathlon.net/nebula_login/in/api/v2/";
	public static final String NEBULA_CREATEUSER_BASE_URL="https://api-global.preprod.decathlon.net/nebula_login/in/api/v2/users/";

	public static final String CREATE_ACTIVITY_URL = "http://fakerestapi.azurewebsites.net/api/v1/Activities";
	public static final String DC_BASE_CREATE_CART_URL="https://api-sg.preprod.decathlon.net/dsi_dosa_cart/v1/";
	public static final String DC_BASE_CREATE_CART_V2_URL="https://api-sg.preprod.decathlon.net/dsi_dosa_cart/v2/";

	public static final String DC_BASE_ADDRESS_URL="https://api-global.preprod.decathlon.net/dsi_dosa_address/api/v1/";
	public static final String DC_BASE_FULFILLER_URL="https://preprodoms.decathlon.in/fulfiller/api/v2/";
	public static final String DC_BASE_AUTH_TOKEN_GENERATION_URL="https://preprod.idpdecathlon.oxylane.com/as/token.oauth2";
	public static final String DC_BASE_COUPON_URL="https://api-global.preprod.decathlon.net/dsi_dosa_coupons/v1/";
	public static final String DC_BASE_SPLIT_FULFILLER_ORDER_URL="https://preprodoms.decathlon.in/fulfiller/api/v3/";
	public static final String DC_BASE_PURCHASE_HISTORY_URL="https://api-global.preprod.decathlon.net/dsi_dosa_purchasehistory/api/v2/";

	
	public static final String OAUTH_TOKEN_AUTHORIZATION="Basic QzUyMWRhMGUxMjNiYWY4ZmQxMzVmZjE3MjM5YmFjY2M0YzIzYWZhMDQ6UzBYdmxOZ3ZwQk5wS3F2TnFoZXBYa1dYdThraUd5SDJxVWxKVmY0eVdxaXpqWHFhUmVzT3JZMEY4amdkWW14eA==";
	public static final String OAUTH_TOKEN_CONTENT_TYPE="application/x-www-form-urlencoded";
	public static final String OAUTH_TOKEN_COOKIE="visid_incap_2082526=S0HUTYFzTmuNbyQ6HyaZ+7+V6l4AAAAAQUIPAAAAAADif3o69Tnm6vV9BPYuvDTn; PF=SkEA4s9X3N7FCKsY0CqgHJ; incap_ses_1138_2082526=LHZWXTUvLAlvQD55m/3KD7vn8V4AAAAAHt72tnW9RjSj0NTrAgxM0Q==; nlbi_2082526=IJx8Uk5nOFIlLa7s/FN0ZQAAAABtCOAyREzeksQQQDx6Ee63; incap_ses_1134_2082526=rqUZEiSHGWxj/qc3B8e8Dz8u9F4AAAAAVNJ2ZZX4E2TeCkwXaiM70w==";

	public static final String DC_CREATE_ORDER_URL="https://preprodoms.decathlon.in/order/api/v2";

}
