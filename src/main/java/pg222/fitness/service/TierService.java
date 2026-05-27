package pg222.fitness.service;

import pg222.fitness.model.Tier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TierService {
    @Autowired
    private FileService fileService;

    public List<Tier> getAllTiers() throws IOException {
        List<Tier> tiers = new ArrayList<>();
        List<String> lines = fileService.readFile("tiers.txt");
        for (String line : lines) {
            String[] parts = line.split(",");
            tiers.add(new Tier(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]), parts[3]));
        }
        return tiers;
    }

    public void updateTier(Tier updatedTier) throws IOException {
        List<Tier> tiers = getAllTiers();
        List<String> newLines = new ArrayList<>();

        for (Tier tier : tiers) {
            if (tier.getTierId() == updatedTier.getTierId()) {
                newLines.add(updatedTier.toString());
            } else {
                newLines.add(tier.toString());
            }
        }

        fileService.writeFile("tiers.txt", newLines);
    }
}