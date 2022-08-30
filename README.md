# Simple Football World Cup Score Board

## Notes
This project is meant to be as simple as possible. No third-party libraries were included.

1. Standard output was used as a replacement for logging
2. In order to properly simulate live feeds multi-threaded scenario is desirable.Thus, synchronized mechanism can be used when performing CRUD operations to make sure events are not modified at the same time by two or more threads (requests if you may) simultaneously
3. TimeOffSets must be taken into account when displaying the scoreboard. That is - better to choose one UTC time and adhere/adjust all other different UTC zones to the desired one. 
4. Dealing with suspended/delayed/canceled events must be taken into account.



