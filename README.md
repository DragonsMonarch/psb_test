Проект построен по принципам Clean Architecture с Mvvm. Используемые технологии:\
  &ensp;*Koin\
  &ensp;*Retrofit\
  &ensp;*RxJava\
Проект разделён на 5 пакетов:\ 
  &ensp;-domain: в нём хранится основная бизнес логика(usecases),\ 
      &ensp;&ensp;&ensp;интерфейсы репозиториев и модели.\
  &ensp;-data: хранит в себе DataHolder для Retrofit и \
     &ensp;&ensp;&ensp; реализацию репозитория для получения котировок\
  &ensp;-presenter: в нём располагаются mainactivity, где находится основной код визуала,\
      &ensp;&ensp;&ensp;mainviewmodel, прослойка для адаптации бизнес логики под визуал\
     &ensp;&ensp;&ensp;ui - данные для тем\
  &ensp;-di:\
   &ensp;&ensp;&ensp;модули коина\
  &ensp;-app:\
    &ensp;&ensp;&ensp;место запуска коина\

  
