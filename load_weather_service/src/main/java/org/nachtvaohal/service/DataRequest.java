package org.nachtvaohal.service;

/**
 * Интерфейс получения данных с удаленного сервиса
 */
public interface DataRequest {
    // TODO: Эти исключения надо будет убрать отсюда наверно.
    void getData() throws Exception;
}