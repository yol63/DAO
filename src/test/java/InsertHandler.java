import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class InsertHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        String json = null;
        try {

            if ("GET".equals(httpExchange.getRequestMethod())) {
                json = URLDecoder.decode(handleGetRequest(httpExchange), StandardCharsets.UTF_8);
                InsertMoney insertMoney = new Gson().fromJson(json, InsertMoney.class);
                Service.InsertMoney(insertMoney.getId_order(),insertMoney.getSumma());
                requestParamValue = "OK!";
            } else if ("POST".equals(httpExchange)) {
                System.out.println("POST");
            }
            handleResponse(httpExchange, requestParamValue,200);

        } catch (Exception e) {
            requestParamValue = "Error! : " + e.toString();
            handleResponse(httpExchange, requestParamValue,500);
        }
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue,int i)  throws  IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(i, requestParamValue.length());
        outputStream.write(requestParamValue.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
