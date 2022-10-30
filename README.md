# CUBE
CUBE interview

# 幣別 DB 維護功能
API : http://localhost:9091/cubeapi/currency

新增
1. BPI Tabel 裡有USD及EUR及GBP資料的話，新增按鈕disable
2. BPI Table 裡如果USD、EUR、GBP缺其中一項，新增按鈕enable
3. 新增畫面幣別下拉選單的內容，會依缺乏的幣別顯示
4. 新增成功導回幣別維護功能頁

搜尋
1. 幣別下拉選單內容是抓取CURRENCY Table 的資料及自訂義全部

更新
1. 按更新按鈕，導到更新頁面
2. 更新成功導回幣別維護功能頁

刪除
1. 按刪除按鈕，如果更新成功導回幣別維護功能頁

# 呼叫 coindesk 的 API
API : http://localhost:9091/cubeapi/coindesk

1. Response data 顯示在頁面上

# 呼叫 coindesk 的 API，並進行資料轉換，組成新 API A. 更新時間（時間格式範例：1990/01/01 00:00:00）B. 幣別相關資訊（幣別，幣別中文名稱，以及匯率）
API : http://localhost:9091/cubeapi/coindeskInfo

# DB
DB檔(CUBE.mv.db)放在 H2File資料夾裡

CREATE TABLE CURRENCY (
ID int auto_increment ,
Currency VARCHAR(50) NOT NULL,
CurrencyCH VARCHAR(50) NOT NULL,
primary key(ID)
)

CREATE TABLE BPI(
ID int auto_increment,
currencyID int,
code VARCHAR(50) NOT NULL,
rate VARCHAR(50) NOT NULL,
description VARCHAR(255),
ratefloat decimal(5,4), 
primary key(ID),
foreign key (currencyID ) references CURRENCY(ID)
)

insert into CURRENCY(ID,CURRENCY,CURRENCYCH) values(1,'USD','美金')
insert into CURRENCY(ID,CURRENCY,CURRENCYCH) values(2,'GBP','英鎊')
insert into CURRENCY(ID,CURRENCY,CURRENCYCH) values(3,'EUR','歐元')

insert into BPI(ID,CURRENCYID,CODE,RATE,DESCRIPTION,RATEFLOAT) values(1,'USD','20,291.9334','United States Dollar',2.2)
insert into BPI(ID,CURRENCYID,CODE,RATE,DESCRIPTION,RATEFLOAT) values(2,'GBP','16,955.7772','British Pound Sterling',3.2)
insert into BPI(ID,CURRENCYID,CODE,RATE,DESCRIPTION,RATEFLOAT) values(3,'EUR','19,767.3057','Euro',1.2)
