# -*- coding: utf-8 -*-
import scrapy


class LaxweatherSpider(scrapy.Spider):
    name = "weather"
    allowed_domains = ["http://www.aviationweather.gov/adds/metars/index?submit=1&station_ids=KLAX&chk_metars=on&hoursStr=8&std_trans=translated"]
    start_urls = ['http://www.aviationweather.gov/adds/metars/index?submit=1&station_ids=KLAX&chk_metars=on&hoursStr=8&std_trans=translated']

    def parse(self, response):
        print("here---------------------------------------")
        print(data.css('strong').extract_first())
        for data in response.css('tbody'):
            print("#############---------------------------------------")
            yield {
                'test': data.css('strong').extract_first(),
                # 'Time': data.css('tr').extract_first(),
                # 'altimeter': data.css('div.tags a.tag::text').extract(),
            }
            print(data)
