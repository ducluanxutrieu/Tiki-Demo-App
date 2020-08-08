# Tiki Demo App

API calls
- Banner API:
- Quick link API:
- Flash Deal API:
> Call Banner API + Quick Link API in parallel , after both of them finish call Flash Deal API

UI
- Render UI in sequential from top to bottom: Banner -> Quick Link -> Flash Deal
- If the API for that block failed, skip the block.
- Ex: Banner ok, Quick Link failed, Flash Deal ok => render: Banner -> Flash Deal
- Display loading
- Pull to refresh

## This project demo have
### MVVM Architecture
<img src="/image/MVVM-architecture.png"/>
- Full architecture only apply for Banner API to show knowledge because requirement is "Render UI in sequential from top to bottom and show loading"

- In this app use Navigation Component, DataBinding, Coroutine, LiveData, RoomDatabase and Retrofit.

1. View: listen pull to refresh
2. ViewModel: handle and call repository to call API
3. Model: 
- 3.1. Repository call API by Retrofit. 
- 3.2. Then save data to RoomDatabase.
- 3.3. View listen data in RoomDatabase change throught LiveData and update UI
- 3.4. If data need handle or calculate ViewModel will handle