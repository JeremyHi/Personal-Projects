import time
import sys
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.ui import Select

# PARTS YOU SHOULD EDIT
###########################################
# Address location of you firefox profile (for autofill)
FIREFOX_PROFILE =  '/Users/Ruht_Roh/Library/Application Support/Firefox/Profiles/xzbpu59q.default'

# Make this an accessory link that already exists in the store you dont want to buy
CHECKOUT_BUFFER = "http://www.supremenewyork.com/shop/accessories/t0oldw67n/bbdxfjcgu"

#Keyword
KEYWORD_ONE = "Short"
KEYWORD_TWO = "Yellow"

# if Item you are trying to buy has a size, then set ITEM_HAS_SIZE to 1, otherwise zero
ITEM_HAS_SIZE = 1
SIZE = "34916" # Put size that you want; Note that it will buy the next bigger size if specified size is out


CUSTOM_AUTOFILL = 0   # Set this to 0 if you dont want to use the firefox autofill plugin
# your checkout details
NAME = "James Harden"
EMAIL = "fakeEmail@gmail.com"
TEL = "6505557382"
ADDRESS = "2519 Poop St"
ZIP = "94010"
CITY = "Burbank"
STATE = "CA"
TYPE = "Visa"
NUMBER = "5940295833728195"
EXP_DATE_MONTH = "06"
EXP_DATE_YEAR = "2017"
CVV = "420"



# DON'T EDIT THIS - fuck it im editing it
###########################################
userItem = sys.argv[1]
TARGET = "http://www.supremenewyork.com/shop/all/" + userItem
CHECKOUT = "https://www.supremenewyork.com/checkout"
CART = "http://www.supremenewyork.com/shop/cart"

HREF = "href"
ITEM = "inner-article"

def selectSize(driver):

    #pick size and check if checkout button is there to checkout
    if ITEM_HAS_SIZE:
        driver.find_element_by_id("size").send_keys(SIZE)
    driver.find_element_by_id("add-remove-buttons").find_element_by_name("commit").click()
    driver.find_element_by_partial_link_text("checkout").click()

def cop(driver):
    #go to supreme new section of shop
    driver.get(TARGET)

    #link string to check if shop has been updated
    req = driver.find_element_by_class_name(ITEM)
    str1 = req.find_element_by_css_selector("a").get_attribute(HREF)

    #mod target to be link of where i want shit to go
    driver.get(TARGET)

#######################################################
    # #Keyword
            #modify target to contain link of where item is located...
                #example: http://www.supremenewyork.com/shop/shorts/ [where 'shorts' is our inputted code string]
    # TARGET = "http://www.supremenewyork.com/shop/new"
    # KEYWORD_ONE = "Short"
    # KEYWORD_TWO = "Yellow"
    # HREF = "href"
    # ITEM = "inner-article"
#######################################################

    





    #selects size and adds to cart
    selectSize(driver)

    #if profile already has custom autofill, do not execute
    if( CUSTOM_AUTOFILL == 0):
        #fill out check form
        driver.find_element_by_id("order_billing_name").send_keys(NAME)
        driver.find_element_by_id("order_email").send_keys(EMAIL)


        tel_input = driver.find_element_by_id("order_tel")
        tel_input.click()
        tel_input.send_keys(TEL)

        driver.find_element_by_id("bo").send_keys(ADDRESS)

        zip_input = driver.find_element_by_id("order_billing_zip")
        zip_input.click()
        zip_input.send_keys(ZIP)

        driver.find_element_by_id("order_billing_city").send_keys(CITY)

        driver.find_element_by_id("order_billing_state").send_keys(STATE)

        driver.find_element_by_id("credit_card_type").send_keys(TYPE)

        card_input = driver.find_element_by_id("cnb")
        card_input.click()
        card_input.send_keys(NUMBER)

        driver.find_element_by_id("credit_card_month").send_keys(EXP_DATE_MONTH)

        driver.find_element_by_id("credit_card_year").send_keys(EXP_DATE_YEAR)

        driver.find_element_by_id("vval").send_keys(CVV)

        driver.find_element_by_tag_name("ins").click()

        driver.find_element_by_xpath(".//*[contains(text(), 'I have read and agree to the')]").click()

    req = driver.find_element_by_id("order_billing_state")
    req.send_keys(STATE)
    req.submit()

    end = time.time()
    print(end - start)

if __name__ == '__main__':

    #define the driver to use the special firefox settings
    fp = webdriver.FirefoxProfile(FIREFOX_PROFILE)
    driver = webdriver.Firefox(fp)
    driver.implicitly_wait(10)

    try:
        cop(driver)
    except Exception:
        print("Test Failed.")
        driver.close()
