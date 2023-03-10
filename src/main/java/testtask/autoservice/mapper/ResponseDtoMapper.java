package testtask.autoservice.mapper;

public interface ResponseDtoMapper<D, T> {

    D mapToDto(T t);
}
