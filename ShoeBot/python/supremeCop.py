import sys
import openpyxl
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.ui import Select

# Address location of you firefox profile (for autofill)
FIREFOX_PROFILE = '/Users/Ruht_Roh/Library/Application Support/Firefox/Profiles/xzbpu59q.default'

#spreadsheetInformation
wb = openpyxl.load_workbook('Customer.xlsx')
sheet = wb.get_sheet_by_name('Sheet1')
customerInfo = []
customerList = []

#fills customerList with the Customer Information.
for row in range(2, sheet.get_highest_row()+1):
    for column in range(1, sheet.get_highest_column()+1):
        customerInfo.append(sheet.cell(row=row,column=column).value)
    customerList.append(customerInfo)
    customerInfo = []

CUSTOM_AUTOFILL = 0   # Set this to 0 if you dont want to use the firefox autofill plugin

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
    driver.get(TARGET)
    
    for a in driver.find_elements_by_css_selector("a.name-link"):
        if a.text == ITEM_NAME:
            for b in driver.find_elements_by_css_selector("a.name-link"):
                if b.text == ITEM_COLOR:
                    b.click()
                    break
            break

    selectSize(driver)

    if (CUSTOM_AUTOFILL == 0):
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

if __name__ == '__main__':

    #define the driver to use the special firefox settings
    fireFox = webdriver.FirefoxProfile(FIREFOX_PROFILE)
    driver = webdriver.Firefox(fireFox)
    driver.implicitly_wait(2)

    try:
        for customer in customerList:
            NAME = customer[0]
            EMAIL = customer[1]
            TEL = customer[2]
            ADDRESS = customer[3]
            ZIP = customer[4]
            CITY = customer[5]
            STATE = customer[6]
            TYPE = customer[7]
            NUMBER = customer[8]
            EXP_DATE_MONTH = customer[9]
            EXP_DATE_YEAR = customer[10]
            CVV = customer[11]
            ITEM_NAME = customer[12]
            ITEM_COLOR = customer[13]

            ITEM_HAS_SIZE = 1 if customer[16] == "NO" else 0
            SIZE = "34916" if customer[14].lower() == "medium" else "34917"

            userItem = customer[15] #arg entered for type of clothing, TODO: make as spreadsheet info.
            TARGET = "http://www.supremenewyork.com/shop/all/" + userItem
            cop(driver)
            driver.find_element_by_tag_name('body').send_keys(Keys.COMMAND + 't')


    except Exception:
        print("Test Failed.")
        driver.close()





