package com.taisiia.travelagency.address;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping("/{id}")
    public AddressDto findAddressById(@PathVariable Long id) {
        return addressMapper.toDto(addressService.findById(id));
    }


    @PostMapping
    public AddressDto saveAddress(@RequestBody @Valid AddressForm addressForm) {
        return addressMapper.toDto(addressService.save(addressMapper.toDao(addressForm), addressForm.getCity()));
    }

    @PutMapping("/{id}")
    public AddressDto updateAddress(@RequestBody @Valid AddressForm addressForm, @PathVariable Long id) {
        return addressMapper.toDto(addressService.update(addressMapper.toDao(addressForm), addressForm.getCity(), id));
    }

    @DeleteMapping("/{id}")
    public void deleteAddressById(@PathVariable Long id) {
        addressService.deleteById(id);
    }

    @GetMapping
    public Page<AddressDto> getAddressPage(@RequestParam int page, @RequestParam int size) {
        return addressService.getAddressPage(PageRequest.of(page, size)).map(addressMapper::toDto);
    }

    @GetMapping("/city")
    public List<AddressDto> getAddressesByCity(@RequestParam String city) {
        return addressMapper.toListDto(addressService.getAddressesByCity(city));
    }


}
