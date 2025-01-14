package AdminServiceTest;


import com.example.backend.model.MajorGroup;
import com.example.backend.repository.MajorGroupRepository;
import com.example.backend.service.adminService.GroupManagementForAdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        MajorGroup majorGroup = new MajorGroup(1L, "Wykład", 1, "2025-01-10", "D6-s201", 90);

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
