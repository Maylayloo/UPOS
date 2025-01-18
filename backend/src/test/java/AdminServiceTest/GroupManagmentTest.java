package AdminServiceTest;


import com.example.backend.model.DayOfTheWeek;
import com.example.backend.model.MajorGroup;
import com.example.backend.repository.MajorGroupRepository;
import com.example.backend.service.adminService.GroupManagementForAdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class GroupManagementForAdminServiceTest {

    @InjectMocks
    private GroupManagementForAdminService groupManagementForAdminService;

    @Mock
    private MajorGroupRepository majorGroupRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateGroup() {
        List<Long> studentIds = Arrays.asList(1L);
        LocalTime startTime = LocalTime.of(9,40);
        LocalTime endTime = LocalTime.of(11,45);
        MajorGroup majorGroup = new MajorGroup( "Wykład", 1, DayOfTheWeek.PIĄTEK,startTime,endTime,"206 B6",15,studentIds);

        groupManagementForAdminService.createGroup(majorGroup);

        verify(majorGroupRepository, times(1)).save(majorGroup);
    }

    @Test
    void testDeleteGroup() {
        Long groupId = 1L;

        groupManagementForAdminService.deleteGroup(groupId);

        verify(majorGroupRepository, times(1)).deleteById(groupId);
    }
}
