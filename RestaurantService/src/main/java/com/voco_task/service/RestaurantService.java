package com.voco_task.service;

import com.voco_task.mapper.IRestaurantMapper;
import com.voco_task.rabbitmq.model.AuthRestaurantModel;
import com.voco_task.repository.IRestaurantRepository;
import com.voco_task.repository.entity.Restaurant;
import com.voco_task.utility.JwtTokenManager;
import com.voco_task.utility.ServiceManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestaurantService extends ServiceManager<Restaurant, String> {

    private final IRestaurantRepository restaurantRepository;
    private final JwtTokenManager jwtTokenManager;
    private final WebClient webClient;

    private static final String BING_MAPS_API_KEY = "AmRg7INu6wzmx_FzvRZqtgiN1WK_xio1G0KfaieFAa37GB6bTYUURMNpMvxDs_To";
    private static final String BASE_URL = "https://dev.virtualearth.net/REST/v1/Locations";

    public RestaurantService(IRestaurantRepository restaurantRepository, JwtTokenManager jwtTokenManager, WebClient.Builder webClientBuilder) {
        super(restaurantRepository);
        this.restaurantRepository = restaurantRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public void createRestaurant(AuthRestaurantModel model) {
        Restaurant restaurant = IRestaurantMapper.INSTANCE.toRegisterRestaurant(model);
        List<Double> latitude = new ArrayList<>();
        List<Double> longitude = new ArrayList<>();
        for (String x : model.getAddress()) {
            Map<String, Double> location = getLocation(x);
            latitude.add(location.get("latitude"));
            longitude.add(location.get("longitude"));
        }
        restaurant.setLatitude(latitude);
        restaurant.setLongitude(longitude);
        save(restaurant);
    }

    //Girilen adresin enlem ve boylam bilgilerinin bulunmasını sağlayan fonksiyon
    public Map<String, Double> getLocation(String address) throws RuntimeException{
        Map<String, Double> coordinate = new HashMap<>();

        String url = "?query=" + address + "&key=" + BING_MAPS_API_KEY;
        String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
        if (!response.isEmpty()) {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray resourceSets = jsonObject.getJSONArray("resourceSets");

            for (int i = 0; i < resourceSets.length(); i++) {
                JSONObject resourceSet = resourceSets.getJSONObject(i);
                JSONArray resources = resourceSet.getJSONArray("resources");

                for (int j = 0; j < resources.length(); j++) {
                    JSONObject resource = resources.getJSONObject(j);
                    JSONObject point = resource.getJSONObject("point");
                    JSONArray coordinates = point.getJSONArray("coordinates");

                    double latitude = coordinates.getDouble(0);
                    double longitude = coordinates.getDouble(1);

                    coordinate.put("latitude", latitude);
                    coordinate.put("longitude", longitude);
                    System.out.println("latitude" + latitude + "longitude" + longitude);
                    return coordinate;
                }
            }
        }

        return null;
    }
    public void Stream() {
        List<String> list3 = new ArrayList<>();//içerisinde değerler olduğunu düşünün
        List<Integer> list2 = new ArrayList<>();//içerisinde değerler olduğunu düşünün
        list3.stream().map(x -> {
            System.out.println(x);
            return x;
        });//Birden fazla satır yazılacaksa süslü parantez kullanılır
        list3.stream().map(x -> x.toUpperCase()).forEach(System.out::println);//Yukarıdaki ile aynı işlem
        list3.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);//küçükten büyüğe tersten sıralama
        //Map foreack den farklı olarak return değeri bekler
        Map<String, Double> map = new HashMap<>();
        map.forEach((k,v) -> System.out.println(k + " - " + v));//Map de key ve value değerlerini döndürür
        List<Integer> list4 = list3.stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());//Bir listeye toplama
        //Gelen her sayının basına ve sonuna"-" işareti
        List<String> list5 = list3.stream().map(x -> " -" + x + "- ").collect(Collectors.toList());
        //filter(Sadece true dönen değerleri alır!!!!
        list2.stream().filter(x -> x > 10).forEach(System.out::println);//-> BU METOT DA BİRLİKTE KULLANILABİLİR <-collect(Collectors.toList());
        //Cift sayıları 10 ile çarpıp listeye atma(map den sonra filter ile devam edilebilir)
        List<Integer> list44 = list2.stream().filter(x -> x % 2 == 0).map(x -> x * 10).collect(Collectors.toList());
        Integer i = list2.stream().filter(x -> x % 2 == 0).map(x -> x * 10).mapToInt(x->x).sum();//sayıların toplamı
        //Gruplayarak mapleme işlemi(groupingBy map leme işlemi yapıyor isek value değeri bir liste olucak)
        Map<Integer, List<String>> map2 = list3.stream().collect(Collectors.groupingBy(x -> x.length()));
        //String değerlerin karşısına ilk iki harfi
        Map<String, String> map3 = list3.stream().collect(Collectors.toMap(x ->x, y->y.substring(0,2)));
    }
}
