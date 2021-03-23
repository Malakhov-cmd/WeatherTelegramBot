import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    //73d55d905833e89864b322b6a8da6d6a
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message +"&units=metric&appid=73d55d905833e89864b322b6a8da6d6a");

        String result = "";
        Scanner in = new Scanner((InputStream) url.getContent());
        while (in.hasNext())
        {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getarray = object.getJSONArray("weather");
        for(int i=0 ; i< getarray.length(); i++)
        {
            JSONObject obj = getarray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }

        return "Town: " + model.getName() + "\n" +
                "Tempereture: " + model.getTemp() + "C"  + "\n" +
                "Humidity: " + model.getHumidity() + "%" + "\n" +
                "Main: " + model.getMain() + "\n" +
                "https://openweathermap.org/img/w/" + model.getIcon() + ".png";
    }
}
