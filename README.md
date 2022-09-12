# Реализация SpringJwtToken для небольшого Rest API.
В основе лежит то, что у нас есть пользователь и у него есть роль:
- Admin
- User
Этапы создание:
1. С помощью liquidbase и двух конфигурационных файлов db.changelog-1.0.xml и db.changelog-master.xml идет создание 2 таблиц
- Users
- Roles
- Связивающая таблица user_roles для связи Many-to-Many;
2. Создание всех сущностей которые реализовывают наши таблици;
3. Создание Repository для Users и Roles
4. Реализация сервисной части для Users для дальнейшего его использования. 
5. Реализация JWT Token:
- В основе реализации выступает JwtUserDetailsService которая имплементит UserDetailsService;
- В этом классе создается JwtUser, который в свою очередь имплементит UserDetails;
- В создании JwtUser используется паттерн Factory Method реализованый в JwtUserFactory;
- Дальше идет JwtTokenProvider который генерирует нам токен для пользователи и его роли(User, Admin);
- JwtTokenFilter валидирует наш JwtTokenProvider (если все ок, мы даем доступ пользователям);
- JwtConfigurer принимает JwtTokenProvider для обработки JwtTokenFilter что б получить доступ;
- JwtAuthenticationException - для обработки исключений;
6. Создание конфигурации в классе SecurityConfig для доступа User и Admin;
7. Rest Controller, реализовано три класса:
- AuthenticationRestControllerV1 для получение токена который нам в дальнейшем дает доступ для User и Admin;
- UserRestControllerV1 предназначен для User что б получить данные какого то пользователя по id 
( Важний момент: для того что б Get запрос прошел успешно, в Postman перед отправлением запроса нам нужно в Headers 
в поле Key прописываем Authorization, а в поле Value мы пишем Bearer_ и добавляем токен, который мы получили через AuthenticationRestControllerV1
пример Bearer_ey************************************ );
- AdminRestControllerV1 работает почти также само, как и UserRestControllerV1 но он получает больше данных о пользователе, которого он ищет. И некоторый поля
которые он видет, недоступны для UserRestControllerV;
8. Реализация Dto под каждый RestController:
- AuthenticationRequestDto дает username. password;
- UserDto для получение данных в UserRestControllerV1;
- AdminUserDto для получение данных в AdminRestControllerV1;
