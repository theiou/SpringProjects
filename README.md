# SpringProjects
Spring Framework приложение библиотека

# SpringLibrary
CRUD Spring приложение с использованием jdbcTemplate

# SpringLibraryWithSpringDataJpa
CRUD Spring приложение с использованием spring data jpa

# Set thing up
Для корректной работы приложений необходимо настроить файл application.properties под себя.
В этом файле нужно указать ссылку на базу данных, логин и пароль для входа.

В базе данных создать две сущности Person(person_id, name, email, yearOfBirth) и Book(book_id, person_id, name, year) с отношением один ко многим
