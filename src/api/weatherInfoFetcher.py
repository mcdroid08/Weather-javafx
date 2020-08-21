from requests import get
import json
import sys


# {query, tempreature, visiblty, humidiry, windSpeed, winddir, }

def write_on_buffer(data):
    for item in data:
        print(item)


def main():
    my_key = "f5f13885b3268a17f8fd05a0a7e0988c"
    search_query = sys.argv[1]
    #search_query = "delhi"

    response_json = json.loads(
        get(f"http://api.weatherstack.com/current?access_key={my_key}&query={search_query}").text)
    # print(response_json)

    result = [response_json["request"]["query"], response_json["current"]["temperature"],
              response_json["current"]["visibility"], response_json["current"]["humidity"],
              response_json["current"]["wind_speed"], response_json["current"]["wind_dir"],
              response_json["current"]["uv_index"]]

    write_on_buffer(result)


if __name__ == '__main__':
    main()
