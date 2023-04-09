package ru.practicum.webService.Interface;

import ru.practicum.cheque.dto.ChequeDto;
import ru.practicum.product.dto.ProductInfo;
import ru.practicum.product.model.Product;
import ru.practicum.model.util.*;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService(name = "ProductService" ,targetNamespace = "http://soap")
public interface ProductWebService {

    @WebResult(name = "Product")
    @RequestWrapper(
            localName = "getAllProductRequest",
            className = "owl.home.KTE.test.webservice.AllProductRequest")
    @ResponseWrapper(
            localName = "getAllProductResponse",
            className = "owl.home.KTE.test.webservice.AllProductResponse")
    List<Product> getAllProduct();

    @WebResult(name = "ProductInfo")
    @RequestWrapper(
            localName = "getAdditionalProductInfoRequest",
            className = "owl.home.KTE.test.webservice.AdditionalProductInfoRequest")
    @ResponseWrapper(
            localName = "getAdditionalProductInfoResponse",
            className = "owl.home.KTE.test.webservice.AdditionalProductInfoResponse")
    ProductInfo getAdditionalProductInfo(
            @WebParam(name = "productId") long productId,
            @WebParam(name = "clientId") long clientId);

    @WebResult(name = "TotalPriceShopingLists")
    @RequestWrapper(
            localName = "getTotalPriceShopingListsRequest",
            className = "owl.home.KTE.test.webservice.TotalPriceShopingListsRequest")
    @ResponseWrapper(
            localName = "getTotalPriceShopingListsResponse",
            className = "owl.home.KTE.test.webservice.TotalPriceShopingListsResponse")
    TotalPriceShopingListResponse totalPriceShopingLists(@WebParam(name = "ShopingList")List<TotalPriceShopingListRequest> shopingList);

    /**
     * Оценка товара
     * @param productId - идентификатор продукта
     * @param clientId - идентификатор клиента
     * @param amountStar - количество звезд
     * @return - информация о продукте
     */
    @WebResult(name = "FeedBackProduct")
    @RequestWrapper(
            localName = "getFeedBackProductRequest",
            className = "owl.home.KTE.test.webservice.FeedBackProductRequest")
    @ResponseWrapper(
            localName = "getFeedBackProductResponse",
            className = "owl.home.KTE.test.webservice.FeedBackProductResponse")
    ProductInfo feedBackProduct(
            @WebParam(name = "productId") long productId,
            @WebParam(name = "clientId") long clientId,
            @WebParam(name = "amountStar") int amountStar);

    /**
     * @param productId - идентификатор продукта
     * @return - статистика продукта
     */
    @WebResult(name = "ProductStatisctic")
    @RequestWrapper(
            localName = "getProductStatiscticRequest",
            className = "owl.home.KTE.test.webservice.ProductStatiscticRequest")
    @ResponseWrapper(
            localName = "getProductStatiscticResponse",
            className = "owl.home.KTE.test.webservice.ProductStatiscticResponse")
    StatisticProduct productStatisctic(@WebParam(name = "productId") long productId);

    /**
     * @param clientId - идентификатор клиента
     * @param totalPrice - итоговая сумма
     * @param shopingList - список запроса итоговой стоимости товара
     * @return - чек
     */
    @WebResult(name = "GenerateCheck")
    @RequestWrapper(
            localName = "getGenerateCheckRequest",
            className = "owl.home.KTE.test.webservice.GenerateCheckRequest")
    @ResponseWrapper(
            localName = "getGenerateCheckResponse",
            className = "owl.home.KTE.test.webservice.GenerateCheckResponse")
    ChequeDto generateCheck(
            @WebParam(name = "clientId") long clientId,
            @WebParam(name = "totalPrice") double totalPrice,
            @WebParam(name = "TotalPriceShopingListRequest") List<TotalPriceShopingListRequest> shopingList);
}
