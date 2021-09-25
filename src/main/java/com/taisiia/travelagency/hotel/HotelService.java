package com.taisiia.travelagency.hotel;

import com.taisiia.travelagency.address.Address;
import com.taisiia.travelagency.address.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final AddressService addressService;
    private final MediaService mediaService;

    public Hotel findById(Long id) {
        return hotelRepository.getById(id);
    }

    public Hotel save(Hotel hotel, String city, String hotelType, MultipartFile photo) {
        hotel.setHotelType(HotelType.safeValueOf(hotelType));
        Address address = addressService.getOrCreate(hotel.getAddress(), city);
        hotel.setPhoto(mediaService.saveFile(photo));
        return hotelRepository.save(hotel);
    }

    @Transactional
    public Hotel update(Hotel hotel, String city, String hotelType, MultipartFile photo, Long id) {

        Hotel hotelToUpdate = findById(id);
        hotelToUpdate.setHotelName(hotel.getHotelName());
        hotelToUpdate.setHotelType(HotelType.safeValueOf(hotelType));
        hotelToUpdate.setDescription(hotel.getDescription());
        hotelToUpdate.setStandard(hotel.getStandard());
        Address address = addressService.getOrCreate(hotel.getAddress(), city);
        hotelToUpdate.setAddress(address);
        hotelToUpdate.setPhoto(mediaService.saveFile(photo));
        return hotelToUpdate;
    }

    public void deleteById(Long id) {
        hotelRepository.deleteById(id);

    }

    public Page<Hotel> getPage(Pageable pageable) {
        return hotelRepository.findAll(pageable);
    }

    public List<Hotel> findByHotelName(String hotelName) {
        return hotelRepository.findAllByHotelNameContaining(hotelName);
    }

    public List<Hotel> findByAddressCityCityName(String cityName) {
        return hotelRepository.findAllByAddressCityCityName(cityName);
    }

    public List<Hotel> findByAddressCityCountryCountryName(String countryName) {
        return hotelRepository.findAllByAddressCityCountryCountryName(countryName);
    }

    public List<Hotel> findByDescriptionContaining(String description) {
        return hotelRepository.findAllByDescriptionContaining(description);
    }

    public List<Hotel> findByStandard(Integer standard) {
        return hotelRepository.findAllByStandard(standard);
    }

    public List<Hotel> findByHotelType(String hotelType) {
        if (HotelType.containsValue(hotelType)) {
            return hotelRepository.findAllByHotelType(HotelType.safeValueOf(hotelType));
        }

        return new ArrayList<>();

    }

    public List<Hotel> findByHotelTypeAndStandardAndAddressCityCityName(String hotelType, Integer standard, String city) {
        if (HotelType.containsValue(hotelType)) {
            return hotelRepository.findAllByHotelTypeAndStandardAndAddressCityCityName(HotelType.safeValueOf(hotelType),
                    standard, city);

        }
        return new ArrayList<>();
    }

    public List<Hotel> findByAddressCityCountryCountryNameAndStandard(String countryName, Integer standard) {
        return hotelRepository.findAllByAddressCityCountryCountryNameAndStandard(countryName, standard);
    }

    public Optional<Hotel> findHotelByUuid(String uuid) {
        return hotelRepository.findByUuid(uuid);
    }
}
