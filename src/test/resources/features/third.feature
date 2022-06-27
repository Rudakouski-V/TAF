Feature: Параметризованные тесты

  Scenario: Простая параметризация
    Given открыт браузер
    * страница логина открыта
    When пользователь с email "atrostyanko@gmail.com" и паролем "Americana#1" залогинился
    Then кнопка добавить проект отображается
    And username есть "Alex Tros"
    And количество проектов - 15

  Scenario Outline: Таблицы
    Given открыт браузер
    * страница логина открыта
    When пользователь с email "<email>" и паролем "<psw>" залогинился
    Then username есть "<visibleText>"
    Examples:
      | email                 | psw         | visibleText |
      | atrostyanko@gmail.com | Americana#1 | Alex Tros   |
      | test@gmail.com        | test1234    | Test User   |