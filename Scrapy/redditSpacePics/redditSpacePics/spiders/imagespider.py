# -*- coding: utf-8 -*-
import os, sys
import scrapy
from scrapy.exceptions import CloseSpider


class ToScrapeCSSSpider(scrapy.Spider):
    name = "toscrape-css"
    start_urls = ['https://www.reddit.com/user/Ruht_Roh/m/spacesubreddits/']

    def parse(self, response):
        if len(os.listdir('Images/full')) > 30:
        	raise CloseSpider(reason='MAX ITEMS DOWNLOADED')

        for quote in response.css("div.thing"):
            if any(x in quote.css("div.entry a::attr(href)").extract_first() for x in ['.jpg', '.png']):
	            yield {
	                'title': quote.css("a.title::text").extract_first(),
	                'author': quote.css("a.author::text").extract_first(),
	                'image_urls': [quote.css("div.entry a::attr(href)").extract_first()],
	            }

        next_page_url = response.css("span.next-button a::attr(href)").extract_first()
        if next_page_url is not None:
            yield scrapy.Request(response.urljoin(next_page_url))
