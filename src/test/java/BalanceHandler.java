import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jdk.internal.jline.internal.Nullable;
import org.apache.commons.lang3.StringEscapeUtils;

import java.lang.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.IOException;
import java.io.OutputStream;

class BalanceHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        String json = null;
        try {

            if ("GET".equals(httpExchange.getRequestMethod())) {
                json = URLDecoder.decode(handleGetRequest(httpExchange), StandardCharsets.UTF_8);
                Integer id_order = new Gson().fromJson(json, Integer.class);
                Integer res = Service.CheckBalance(id_order);
                requestParamValue = res.toString();
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