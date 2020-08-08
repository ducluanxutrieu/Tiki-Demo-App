# Tiki-Demo-App

API calls
Banner API:
curl https://api.tiki.vn/v2/home/banners/v2
Quick link API:
curl https://api.tiki.vn/shopping/v2/widgets/quick_link
Flash Deal API:
curl https://api.tiki.vn/v2/widget/deals/hot
Call Banner API + Quick Link API in parallel , after both of them finish call Flash Deal API

UI
Render UI in sequential from top to bottom: Banner -> Quick Link -> Flash Deal
If the API for that block failed, skip the block.
Ex: Banner ok, Quick Link failed, Flash Deal ok => render: Banner -> Flash Deal
Display loading
Pull to refresh

## This project demo have
### MVVM Architecture
<img src="/image/MVVM-architecture.png"/>
Full architecture only apply for Banner API to show knowledge because requirement is "Render UI in sequential from top to bottom and show loading"
In this app use Databinding, Coroutine, LiveData, RoomDatabase and Retrofit.